package com.samsungsds.eshop.order;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Iterables;
import com.samsungsds.eshop.cart.CartItem;
import com.samsungsds.eshop.cart.CartService;
import com.samsungsds.eshop.payment.Money;
import com.samsungsds.eshop.payment.PaymentRequest;
import com.samsungsds.eshop.payment.PaymentService;
import com.samsungsds.eshop.product.Product;
import com.samsungsds.eshop.product.ProductService;
import com.samsungsds.eshop.shipping.ShippingRequest;
import com.samsungsds.eshop.shipping.ShippingResult;
import com.samsungsds.eshop.shipping.ShippingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/checkouts")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final ShippingService shippingService;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final ProductService productService;

    public OrderController(final OrderService orderService,
                           final ShippingService shippingService,
                           final PaymentService paymentService,
                           final CartService cartService,
                           final ProductService productService) {
        this.orderService = orderService;
        this.shippingService = shippingService;
        this.paymentService = paymentService;
        this.cartService = cartService;
        this.productService = productService;
    }


    @GetMapping(value = "/orders")
    public ResponseEntity<List<OrderItemDTO>> getOrderItems(){
        List<OrderItem> orderItemList = orderService.getOrderItems();
        List<OrderItemDTO> result = orderItemList.stream()
                .map(OrderItemDTO::new)
                .peek(dto -> {
                    List<OrderProduct> orderProducts = orderService.getOrderProducts(dto.getId());
                    dto.setOrderProducts(orderProducts);
                    ShippingResult shippingInfo = shippingService.getShippingResultByOrderId(dto.getId());
                    dto.setShippingStatus(shippingInfo.getStatus());
                }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/orders/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
        } catch (Exception e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<OrderResult> placeOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("placeOrder : " + orderRequest);

        // cart 조회
        CartItem[] cartItems = Iterables.toArray(cartService.getCartItems(), CartItem.class);

        // cart 상품 조회
        Product[] products = getProducts(cartItems);

        // 상품 가격 합계 계산
        Money itemPrice = orderService.calculateItemPrice(cartItems, products);
        logger.info("total item price : " + itemPrice);

        // 예상 배송비 계산
        Money shippingCost = shippingService.calculateShippingCostFromCartItems(cartItems);

        // 결제 요청
        PaymentRequest request = new PaymentRequest(orderRequest.getCreditCardInfo(), itemPrice.plus(shippingCost));
        paymentService.requestPayment(request);

        // 주문ID 생성
        String orderId = orderService.createOrderId(); // VISUAL ID


        // 배송 요청
        ShippingRequest shippingRequest = new ShippingRequest(cartItems, orderRequest.getAddress());
        ShippingResult shippingResult = shippingService.shipOrder(shippingRequest);
        logger.info("shippingCost : " + shippingResult.getShippingCost());

        // 총액 계산
        Money totalCost = itemPrice.plus(shippingResult.getShippingCost());
        //TODO: 주문생성 데이터 생성 필요함.
        OrderItem newOrderItem = new OrderItem(
                orderRequest.getEmailAddress(),
                orderRequest.getAddress().toString(),
                orderRequest.getCreditCardInfo().toString(),
                cartItems,
                shippingResult.getId()
        );
        OrderItem order = orderService.createOrder(newOrderItem);
        shippingResult.setOrderId(order.getId());
        shippingService.saveShipping(shippingResult);
        // 카트 비우기
        cartService.emptyCart();
        return ResponseEntity.ok(new OrderResult(orderId, shippingResult.getShippingTrackingId(),
                shippingResult.getShippingCost(), totalCost));
    }

    private Product[] getProducts(CartItem[] cartItems) {
        String[] cartItemIds = Stream.of(cartItems).map(CartItem::getId).toArray(String[]::new);
        return productService.fetchProductsByIds(cartItemIds).getProducts();
    }
}
