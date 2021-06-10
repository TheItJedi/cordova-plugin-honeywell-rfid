package com.stericycle.cordova.plugin.honeywell.rfid.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.stericycle.cordova.plugin.honeywell.common.RFIDReaderManager;
import com.honeywell.rfidservice.RfidManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class DisconnectRFIDReader extends HoneywellAction {

    private static final String TAG = "com.stericycle.cordova.plugin.honeywell.rfid.action.DisconnectRFIDReader";
    public static final String ACTION_NAME = "disconnectRFIDReader";

    public DisconnectRFIDReader(String action, JSONArray args, CallbackContext callbackContext,
                             CordovaInterface cordova, RFIDReaderManager readerManager, RfidManager rfidManagerr) {
        super(action, args, callbackContext, cordova, readerManager,  rfidManagerr);
    }

    public static final String JSON_ARGS_MAC_ADDRESS = "macaddress";
    public static final String[] JSON_ARGS = { JSON_ARGS_MAC_ADDRESS };
    public static final String MAC_ADDRESS_REQUIRED = "Mac Address is Required";
    public static final String NOREADER_CONNECTED = "Unable to Connect to Reader";
    public static final String UNABLE_TODISCONNECT = "Unable to Disconnect to Reader";
    @Override
    public void run() {
        try {
            // check aidc manager (really necessary?)
            if(this.rfidManager != null) {

                // check for already connected barcode reader
                if(this.readerManager.getInstance() != null) {

                    // get optional name parameter
                    String[] emptyArray = {};
                    JSONObject jsonArgs = super.checkJsonArgs(args, emptyArray);
                    String macaddress = jsonArgs.optString(JSON_ARGS_MAC_ADDRESS,"0C:23:69:19:6C:71");

                    try
                    {
                        // create new barcode reader with no properties set
                        if(macaddress == null) {
                            this.callbackContext.error(MAC_ADDRESS_REQUIRED);
                        }
                        else
                        {
                            if(this.readerManager.getInstance() != null  || this.rfidManager.isConnected()){
                                Boolean disconnected = this.rfidManager.disconnect();
                                if(!disconnected) {
                                    this.readerManager.setInstance(null);
                                    this.callbackContext.success();
                                }else {
                                    this.callbackContext.error(UNABLE_TODISCONNECT);
                                }

                            }
                            else{
                                this.callbackContext.error(NOREADER_CONNECTED);
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        this.callbackContext.error(e.getMessage());
                        CordovaPluginLog.e(TAG, e.getMessage(), e);
                    }
                }
                else
                {

                    this.callbackContext.error(NO_CONNECTED_DEVICES);
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