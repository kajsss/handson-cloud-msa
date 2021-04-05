package com.samsungsds.eshop.shipping;

import java.util.List;

import com.samsungsds.eshop.payment.Money;

import javassist.NotFoundException;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShippingController {
    private final Logger logger = LoggerFactory.getLogger(ShippingController.class);
    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping(value = "/checkouts/shippings/cost")
    public ResponseEntity<Money> calculateShippingCost(@RequestBody List<ShippingItem> shippingList) {
        logger.info("calculateShippingCost");
        int itemCount = shippingList.stream()
                .map(ShippingItem::getQuantity)
                .reduce(0, Integer::sum);
        Money shippingCost = shippingService.calculateShippingCostFromCount(itemCount);
        logger.info("shippingCost : " + shippingCost);
        return ResponseEntity.ok(shippingCost);
    }


    @GetMapping(value = "/shippings")
    public ResponseEntity<ShippingResult> getShipping(@RequestParam Integer orderId) {
        ShippingResult shippingResult = shippingService.getShippingResultByOrderId(orderId);
        if (shippingResult == null || orderId == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shippingResult);
    }
}

