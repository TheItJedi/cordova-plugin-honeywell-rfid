package com.stericycle.cordova.plugin.honeywell.rfid.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.honeywell.rfidservice.RfidManager;
import com.stericycle.cordova.plugin.honeywell.common.RFIDReaderManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class SetAutoReadOnTriggerAction extends HoneywellAction {

    private static final String TAG = "com.stericycle.cordova.plugin.honeywell.rfid.action.setAutoReadOnTriggerAction";
    public static final String ACTION_NAME = "setAutoReadOnTrigger";

    public SetAutoReadOnTriggerAction(String action, JSONArray args, CallbackContext callbackContext,
                             CordovaInterface cordova, RFIDReaderManager readerManager, RfidManager rfidManagerr) {
        super(action, args, callbackContext, cordova, readerManager,  rfidManagerr);
    }

    public static final String JSON_ARGS_SET_AUTO_READ = "autoRead";
    public static final String[] JSON_ARGS = { JSON_ARGS_SET_AUTO_READ };
    @Override
    public void run() {
        try {
            // check aidc manager (really necessary?)
            if(this.rfidManager != null) {


                // get optional name parameter
                String[] emptyArray = {};
                JSONObject jsonArgs = super.checkJsonArgs(args, emptyArray);
                Boolean autoReadOnTrigger = jsonArgs.optBoolean(JSON_ARGS_SET_AUTO_READ,true);

                try
                {
                    this.readerManager.autoReadOnTrigger = autoReadOnTrigger;
                    this.callbackContext.success();
                }
                catch (Exception e)
                {
                    this.callbackContext.error(e.getMessage());
                    CordovaPluginLog.e(TAG, e.getMessage(), e);
                }

            }
            else
            {
                callbackContext.error(RFIDMGR_NOT_INIT);
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }
}