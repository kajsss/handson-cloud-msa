package com.samsungsds.eshop.order;

import com.samsungsds.eshop.cart.CartItem;

import javax.persistence.*;
import java.util.List;

public class OrderItemDTO {

    private Integer id;
    private String productIds; // id, id, id

    private String emailAddress;
    private String address;
    private String creditCardInfo;

    private String shippingStatus;

    private List<OrderProduct> orderProducts;

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.emailAddress = orderItem.getEmailAddress();
        this.address = orderItem.getAddress();
        this.creditCardInfo = orderItem.getCreditCardInfo();
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

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }
}