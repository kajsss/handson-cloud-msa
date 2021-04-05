package com.samsungsds.eshop.shipping;

import java.util.Arrays;

import com.samsungsds.eshop.cart.CartItem;

public class ShippingRequest {
    private CartItem[] cartItems;
    private Address shippingAddress;
    private String orderId;

    public ShippingRequest( CartItem[] cartItems, Address shippingAddress) {
        this.cartItems = cartItems;
        this.shippingAddress = shippingAddress;
    }

    public CartItem[] getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItem[] cartItems) {
        this.cartItems = cartItems;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "ShippingRequest{" +
                "cartItems=" + Arrays.toString(cartItems) +
                ", shippingAddress=" + shippingAddress +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
