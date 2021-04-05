package com.samsungsds.eshop.order;

import com.samsungsds.eshop.cart.CartItem;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String emailAddress;
    private String address;
    private String creditCardInfo;
    private Integer shippingId;

    @Transient
    private CartItem[] cartItems;

    public OrderItem() {

    }

    public OrderItem(String emailAddress, String address, String creditCardInfo, CartItem[] cartItems, Integer shippingId) {
        this.emailAddress = emailAddress;
        this.address = address;
        this.creditCardInfo = creditCardInfo;
        this.cartItems = cartItems;
        this.shippingId = shippingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            "}";
    }

    public CartItem[] getCartItems() {
        return cartItems;
    }

    public void setCartItems(CartItem[] cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }
}