package com.example.karchunkan.fyp;

/**
 * Created by karchunkan on 15/8/2018.
 */

public enum ApiAddress {
    CustNotice("/customer/custNoice.php"),
    DeliveryReq("/customer/deliveryReq.php"),
    Payment("/customer/payment.php"),
    Register("/customer/register.php"),
    Status("/customer/status.php"),

    Delivery("/driver/delivery.php"),
    Package("/driver/package.php"),
    Pickup("/driver/pickup.php"),
    Schedule("/driver/schedule.php"),
    UpdateLocation("/driver/updateLocation.php"),

    Login("/login_app.php");

    private final String address;

    private ApiAddress(String s){
        address=s;
    }

    public String getAddress(){
        return this.address;
    }
}
