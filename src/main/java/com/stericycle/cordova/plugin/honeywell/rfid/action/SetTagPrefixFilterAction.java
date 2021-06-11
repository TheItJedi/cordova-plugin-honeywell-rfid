package com.stericycle.cordova.plugin.honeywell.rfid.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.stericycle.cordova.plugin.honeywell.common.RFIDReaderManager;
import com.honeywell.rfidservice.RfidManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class SetTagPrefixFilterAction extends HoneywellAction {

    private static final String TAG = "com.stericycle.cordova.plugin.honeywell.rfid.action.setTagPrefixFilterAction";
    public static final String ACTION_NAME = "setTagPrefixFilter";

    public SetTagPrefixFilterAction(String action, JSONArray args, CallbackContext callbackContext,
                                    CordovaInterface cordova, RFIDReaderManager readerManager, RfidManager rfidManagerr) {
        super(action, args, callbackContext, cordova, readerManager,  rfidManagerr);
    }

    public static final String JSON_ARGS_TAG_PREFIX = "tagPrefix";
    public static final String[] JSON_ARGS = { JSON_ARGS_TAG_PREFIX };
    @Override
    public void run() {
        try {
            if(this.rfidManager != null) {


                // get optional name parameter
                String[] emptyArray = {};
                JSONObject jsonArgs = super.checkJsonArgs(args, emptyArray);
                String tagPrefix = jsonArgs.optString(JSON_ARGS_TAG_PREFIX,"");

                try
                {
                    this.readerManager.tagPrefixFilter = tagPrefix;
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