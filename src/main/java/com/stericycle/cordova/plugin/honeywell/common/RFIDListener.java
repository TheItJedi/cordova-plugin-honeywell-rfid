package com.stericycle.cordova.plugin.honeywell.common;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.honeywell.rfidservice.EventListener;
import com.honeywell.rfidservice.RfidManager;
import com.honeywell.rfidservice.TriggerMode;
import com.honeywell.rfidservice.rfid.RfidReader;

import org.apache.cordova.CordovaInterface;

public class RFIDListener implements EventListener{
    private static final String TAG = "com.stericycle.cordova.plugin.honeywell.common.RFIDListener";
    private RFIDReaderManager readerManager;
    private RfidManager rfidManager;

    public RFIDListener(RfidManager idmgr, RFIDReaderManager readerMgr){
        readerManager = readerMgr;
        rfidManager = idmgr;
    }
    @Override
    public void onDeviceConnected(Object o) {
        CordovaPluginLog.i(TAG,"onDeviceConnected");
        readerManager.macAddress = o.toString();
        if(readerManager.getInstance() == null){
            rfidManager.createReader();
        }

    }

    @Override
    public void onDeviceDisconnected(Object o) {
        CordovaPluginLog.i(TAG,"onDeviceDisconnected");
    }

    @Override
    public void onReaderCreated(boolean success, RfidReader rfidReader) {
        if (success) {
            readerManager.setInstance(rfidReader);
            CordovaPluginLog.i(TAG, "onReaderCreated");
        } else{
            CordovaPluginLog.e(TAG, "Reader Not Created");
        }
    }

    @Override
    public void onRfidTriggered(boolean b) {
        CordovaPluginLog.i(TAG,"onRfidTriggered");
    }

    @Override
    public void onTriggerModeSwitched(TriggerMode triggerMode) {
        CordovaPluginLog.i(TAG,"onTriggerModeSwitched");

    }
}
