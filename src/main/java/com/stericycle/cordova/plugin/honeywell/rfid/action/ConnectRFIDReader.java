package com.stericycle.cordova.plugin.honeywell.rfid.action;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.stericycle.cordova.plugin.honeywell.common.RFIDReaderManager;
import com.honeywell.rfidservice.RfidManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectRFIDReader extends HoneywellAction {

    private static final String TAG = "com.stericycle.cordova.plugin.honeywell.rfid.action.ConnectRFIDReader";
    public static final String ACTION_NAME = "connectRFIDReader";

    public ConnectRFIDReader(String action, JSONArray args, CallbackContext callbackContext,
                             CordovaInterface cordova, RFIDReaderManager readerManager, RfidManager rfidManagerr) {
        super(action, args, callbackContext, cordova, readerManager,  rfidManagerr);
    }

    public static final String JSON_ARGS_MAC_ADDRESS = "macaddress";
    public static final String JSON_ARGS_ANT_READ_POWER = "antReadPower";
    public static final String JSON_ARGS_ANT_WRITE_POWER = "antWritePower";
    public static final String[] REQ_JSON_ARGS = { JSON_ARGS_MAC_ADDRESS };
    public static final String MAC_ADDRESS_REQUIRED = "Mac Address is Required";
    public static final String UNABLE_TOCONNECT = "Unable to Connect to Reader";
    @Override
    public void run() {
        try {
            if(this.rfidManager != null) {

                // check for already connected barcode reader
                if(this.readerManager.getInstance() == null) {

                    JSONObject jsonArgs = super.checkJsonArgs(args, REQ_JSON_ARGS);
                    String macaddress = jsonArgs.getString(JSON_ARGS_MAC_ADDRESS);
                    int readPower = jsonArgs.optInt(JSON_ARGS_ANT_READ_POWER,3000);
                    int writePower = jsonArgs.optInt(JSON_ARGS_ANT_WRITE_POWER,3000);
                    this.readerManager.antennaReadPow = readPower;
                    this.readerManager.antennaWritePow = writePower;
                    try
                    {
                        // create new barcode reader with no properties set
                        if(macaddress == null) {
                            this.callbackContext.error(MAC_ADDRESS_REQUIRED);
                        }
                        else
                        {
                            //Do quick LEScan then stop
                            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                            mBluetoothAdapter.startLeScan(mLeScanCallback);
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mBluetoothAdapter.stopLeScan(mLeScanCallback);
                            if(this.rfidManager.connect(macaddress)) {

                                this.callbackContext.success();
                            }
                            else{
                                this.callbackContext.error(UNABLE_TOCONNECT);
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
                    // a reader is already connected
                    this.callbackContext.error(RFID_READER_ALREADY_ADDED);
                }
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
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

        }
    };
}