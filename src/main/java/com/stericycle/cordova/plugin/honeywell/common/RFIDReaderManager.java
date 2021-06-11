package com.stericycle.cordova.plugin.honeywell.common;

import com.honeywell.rfidservice.RfidManager;
import com.honeywell.rfidservice.rfid.RfidReader;

// This class manages the instance of the BarcodeReader
public class RFIDReaderManager {

    private RfidReader mRFIDReader;
    public String macAddress ;
    public String tagPrefixFilter;
    public Boolean autoReadOnTrigger;
    public RFIDReaderManager(){
        macAddress = "";
        tagPrefixFilter = "";
        autoReadOnTrigger = true;
    }
    public void setInstance(RfidReader reader)
    {
        mRFIDReader = reader;
    }

    public RfidReader getInstance()
    {
        return mRFIDReader;
    }
}