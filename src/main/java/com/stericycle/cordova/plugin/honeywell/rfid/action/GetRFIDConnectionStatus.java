package com.stericycle.cordova.plugin.honeywell.rfid.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.honeywell.rfidservice.RfidManager;
import com.stericycle.cordova.plugin.honeywell.common.RFIDConnectionStatus;
import com.stericycle.cordova.plugin.honeywell.common.RFIDReaderManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.stericycle.cordova.plugin.honeywell.common.RFIDReaderManager;
import com.honeywell.rfidservice.RfidManager;
import com.stericycle.cordova.plugin.honeywell.common.RFIDStatusEvent;
import com.stericycle.cordova.plugin.honeywell.json.model.JsonRFIDConnectionStatus;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetRFIDConnectionStatus extends HoneywellAction {

    private static final String TAG = "com.stericycle.cordova.plugin.honeywell.rfid.action.GetRFIDConnectionStatus";
    public static final String ACTION_NAME = "getRFIDConnectionStatus";
    private RFIDConnectionStatus _status;
    public GetRFIDConnectionStatus(String action, JSONArray args, CallbackContext callbackContext,
                             CordovaInterface cordova, RFIDReaderManager readerManager, RfidManager rfidManagerr, RFIDConnectionStatus status) {
        super(action, args, callbackContext, cordova, readerManager,  rfidManagerr);
        _status = status;
    }

    @Override
    public void run() {
        try {
            if(this.rfidManager != null) {

                this.callbackContext.success(JsonRFIDConnectionStatus.toJson(_status));
            }
            else
            {
                // aidc manager is initialized from pluginInitialize method.
                // this error below should never occur.
                callbackContext.error(RFIDMGR_NOT_INIT);
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }
}