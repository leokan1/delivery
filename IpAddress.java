package com.example.karchunkan.fyp;

/**
 * Created by karchunkan on 15/8/2018.
 */

public enum IpAddress {
    IP_ADDRESS("192.168.10.6/api");
//    IP_ADDRESS("144.214.100.50/api");

    private final String ip;

    private IpAddress(String s){
        ip=s;
    }

    public String getIp(){
        return this.ip;
    }
}
