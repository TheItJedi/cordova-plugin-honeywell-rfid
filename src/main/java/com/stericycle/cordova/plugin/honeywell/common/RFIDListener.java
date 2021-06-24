package com.stericycle.cordova.plugin.honeywell.common;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.honeywell.rfidservice.EventListener;
import com.honeywell.rfidservice.RfidManager;
import com.honeywell.rfidservice.TriggerMode;
import com.honeywell.rfidservice.rfid.AntennaPower;
import com.honeywell.rfidservice.rfid.OnTagReadListener;
import com.honeywell.rfidservice.rfid.Region;
import com.honeywell.rfidservice.rfid.RfidReader;
import com.honeywell.rfidservice.rfid.RfidReaderException;
import com.honeywell.rfidservice.rfid.TagAdditionData;
import com.honeywell.rfidservice.rfid.TagReadData;
import com.honeywell.rfidservice.rfid.TagReadOption;
import com.stericycle.cordova.plugin.honeywell.rfid.RFIDStatusHandler;
import com.stericycle.cordova.plugin.honeywell.rfid.ReadTagHandler;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;

public class RFIDListener implements EventListener,OnTagReadListener {
    private static final String TAG = "com.stericycle.cordova.plugin.honeywell.common.RFIDListener";
    private RFIDReaderManager readerManager;
    private RfidManager rfidManager;
    RFIDStatusHandler rfidStatusHandler = new RFIDStatusHandler();
    ReadTagHandler readTagHandler = new ReadTagHandler();
    public RFIDListener(RfidManager idmgr, RFIDReaderManager readerMgr){
        readerManager = readerMgr;
        rfidManager = idmgr;
    }
    public void setStatusEventCallback(CallbackContext callbackContext) {
        this.rfidStatusHandler.setCallBack(callbackContext);
    }
    public void setReadTagEventCallback(CallbackContext callbackContext) {
        this.readTagHandler.setCallBack(callbackContext);
    }
    public RFIDConnectionStatus GetRFIDStatus() {
        CordovaPluginLog.i(TAG,"GetRFIDStatus macAddress" + this.readerManager.macAddress);
        if (this.readerManager.macAddress == null || this.readerManager.macAddress == "") {
            return RFIDConnectionStatus.NotConnected();
        }
        else{
            if(isReaderAvailable()) {
                return RFIDConnectionStatus.DeviceAndReaderConnected(this.readerManager.macAddress);
            }
            else
            {
                return RFIDConnectionStatus.DeviceConnected(this.readerManager.macAddress);
            }
        }
       
    }
    @Override
    public void onDeviceConnected(Object o) {
        CordovaPluginLog.i(TAG,"onDeviceConnected");
        this.rfidStatusHandler.onRFIDStatusEvent(RFIDStatusEvent.DeviceConnectedEvent(o.toString()));
        readerManager.macAddress = o.toString();
        if(readerManager.getInstance() == null){
                rfidManager.createReader();
        }

    }

    @Override
    public void onDeviceDisconnected(Object o) {
        CordovaPluginLog.i(TAG,"onDeviceDisconnected");
        this.rfidStatusHandler.onRFIDStatusEvent(RFIDStatusEvent.DeviceDisConnectedEvent(o.toString()));
        if(this.readerManager.getInstance() != null){
            readerManager.setInstance(null);
        }
        readerManager.macAddress = "";
    }

    @Override
    public void onReaderCreated(boolean success, RfidReader rfidReader) {
        if (success) {
            this.rfidStatusHandler.onRFIDStatusEvent(RFIDStatusEvent.ReaderCreatedEvent());
            readerManager.setInstance(rfidReader);
            try {
                AntennaPower[] pow = rfidReader.getAntennaPower();
                if(pow.length >= 1)
                {
                    boolean setPower = false;
                    if(pow[0].getReadPower() != readerManager.antennaReadPow) {
                        pow[0].setReadPower(readerManager.antennaReadPow);
                        setPower = true;
                    }
                    if(pow[0].getWritePower() != readerManager.antennaWritePow) {
                        pow[0].setWritePower(readerManager.antennaWritePow);
                        setPower = true;
                    }
                    if(setPower)
                        rfidReader.setAntennaPower(pow);
                    
                }
                rfidReader.setRegion(readerManager.readerRegion);
            } catch (RfidReaderException e) {
                e.printStackTrace();
            }
            CordovaPluginLog.i(TAG, "onReaderCreated");
        } else{
            CordovaPluginLog.e(TAG, "Reader Not Created");
        }
    }
    private boolean isReaderAvailable() {
        return readerManager.getInstance() != null && readerManager.getInstance().available();
    }
    @Override
    public void onRfidTriggered(boolean b){
        CordovaPluginLog.i(TAG,"onRfidTriggered");
        this.rfidStatusHandler.onRFIDStatusEvent(RFIDStatusEvent.RFIDReaderTriggered(b));
        if(b){
            if(isReaderAvailable()){
                if(readerManager.autoReadOnTrigger) {
                    this.rfidStatusHandler.onRFIDStatusEvent(RFIDStatusEvent.StartStopRead(true));
                    readerManager.getInstance().setOnTagReadListener(this);
                    readerManager.getInstance().read(TagAdditionData.NONE, new TagReadOption());
                }
            }
        }
        else{
            if (isReaderAvailable()) {
                if(readerManager.autoReadOnTrigger) {
                    this.rfidStatusHandler.onRFIDStatusEvent(RFIDStatusEvent.StartStopRead(false));
                    readerManager.getInstance().stopRead();
                    readerManager.getInstance().removeOnTagReadListener(this);
                }
            }
        }
    }

    @Override
    public void onTriggerModeSwitched(TriggerMode triggerMode) {
        CordovaPluginLog.i(TAG,"onTriggerModeSwitched");

    }
    @Override
    public void onTagRead(final TagReadData[] t) {
        CordovaPluginLog.i(TAG,"onTagRead");
        for (TagReadData trd : t) {
            String epc = trd.getEpcHexStr();
            Boolean sendEvent = true;
            if(this.readerManager.tagPrefixFilter != null && this.readerManager.tagPrefixFilter != ""){
                sendEvent = epc.startsWith(this.readerManager.tagPrefixFilter);
            }

            if(sendEvent) {
                this.rfidStatusHandler.onRFIDStatusEvent(RFIDStatusEvent.ReadTagEvent(epc));
                this.readTagHandler.onReadTagEvent(trd);
            }
        }
    }
}
