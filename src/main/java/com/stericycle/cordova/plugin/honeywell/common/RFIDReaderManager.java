package com.stericycle.cordova.plugin.honeywell.common;

import com.honeywell.rfidservice.RfidManager;
import com.honeywell.rfidservice.rfid.Region;
import com.honeywell.rfidservice.rfid.RfidReader;

// This class manages the instance of the BarcodeReader
public class RFIDReaderManager {

    private RfidReader mRFIDReader;
    public String macAddress ;
    public String tagPrefixFilter;
    public Boolean autoReadOnTrigger;
    public int antennaReadPow;
    public int antennaWritePow;
    public Region readerRegion;
    public RFIDReaderManager(){
        macAddress = "";
        tagPrefixFilter = "";
        autoReadOnTrigger = true;
        antennaReadPow = 3000;
        antennaWritePow = 3000;
        readerRegion = Region.NA;
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