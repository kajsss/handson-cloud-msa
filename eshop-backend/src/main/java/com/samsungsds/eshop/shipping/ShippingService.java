package com.samsungsds.eshop.shipping;

import java.math.RoundingMode;
import java.util.UUID;
import java.util.stream.Stream;

import com.google.common.math.DoubleMath;
import com.samsungsds.eshop.cart.CartItem;
import com.samsungsds.eshop.payment.Money;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    final ShippingRepository shippingRepository;


    private static final Logger logger = LoggerFactory.getLogger(ShippingService.class);

    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    Money calculateShippingCostFromCount(final int count) {
        if(count == 0) {
            return new Money("USD", 0, 0);
        }
        double p = 1 + (count * 0.2d);
        double shippingCostUsd = count + Math.pow(3, p);
        logger.debug("shipping cost : {} USD", shippingCostUsd);
        return new Money("USD"
                , DoubleMath.roundToLong(shippingCostUsd, RoundingMode.FLOOR)
                , DoubleMath.roundToLong(Double.valueOf(shippingCostUsd - Math.floor(shippingCostUsd)) * 1000000000, RoundingMode.FLOOR));
    }

    public Money calculateShippingCostFromCartItems(CartItem[] cartItems) {
        int itemCount = Stream.of(cartItems).map(CartItem::getQuantity).reduce(0, Integer::sum);
        return this.calculateShippingCostFromCount(itemCount);
    }

    public ShippingResult shipOrder(ShippingRequest shippingRequest) {
        Money shippingCost = this.calculateShippingCostFromCartItems(shippingRequest.getCartItems());
        String shippingTrackingId = createShippingTrackingId(shippingRequest);
        ShippingResult createdShippingResult = new ShippingResult(shippingTrackingId, shippingCost);

        return createdShippingResult;
    }

    public ShippingResult saveShipping(ShippingResult shippingRequest) {
        return shippingRepository.save(shippingRequest);
    }

    public ShippingResult getShippingResultByOrderId(Integer orderId){
        return shippingRepository.findShippingResultByOrderId(orderId);
    }


    private String createShippingTrackingId(ShippingRequest shippingRequest) {
        // 원래는 뭔가 많이 해야 하지만 예제이므로 그냥 UUID
        return "SHIPPING-" + UUID.randomUUID().toString();
    }
}
