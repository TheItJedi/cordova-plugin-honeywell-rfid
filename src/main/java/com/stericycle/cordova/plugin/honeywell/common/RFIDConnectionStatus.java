package com.stericycle.cordova.plugin.honeywell.common;
public class RFIDConnectionStatus {
    public boolean DeviceConnected;
    public boolean ReaderCreated;
    public String MacAddress;
    
    public static RFIDConnectionStatus DeviceConnected(String macAddress){
        RFIDConnectionStatus status = new RFIDConnectionStatus();
        status.DeviceConnected = true;
        status.ReaderCreated = false;
        status.MacAddress = macAddress;
        return status;
    }
    public static RFIDConnectionStatus DeviceAndReaderConnected(String macAddress){
        RFIDConnectionStatus status = new RFIDConnectionStatus();
        status.ReaderCreated = true;
        status.DeviceConnected = true;
        status.MacAddress = macAddress;
        return status;
    }
    public static RFIDConnectionStatus NotConnected(){
        RFIDConnectionStatus status = new RFIDConnectionStatus();
        status.ReaderCreated = false;
        status.DeviceConnected = false;
        status.MacAddress = "";
        return status;
    }
}
