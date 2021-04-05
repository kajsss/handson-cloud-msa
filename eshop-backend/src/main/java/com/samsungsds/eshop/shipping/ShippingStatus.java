package com.samsungsds.eshop.shipping;

enum ShippingStatus {
    READY("ready"), SHIPPING("shipping"), COMPLETED("completed");

    public String value;

    ShippingStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
