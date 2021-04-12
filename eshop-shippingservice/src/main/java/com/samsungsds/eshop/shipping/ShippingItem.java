package com.samsungsds.eshop.shipping;

import java.io.Serializable;

public class ShippingItem implements Serializable {
    private String productId;
    private int quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShippingItem{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
