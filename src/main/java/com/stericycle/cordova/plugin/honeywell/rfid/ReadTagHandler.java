package com.stericycle.cordova.plugin.honeywell.rfid;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.honeywell.rfidservice.rfid.TagReadData;
import com.stericycle.cordova.plugin.honeywell.common.RFIDStatusEvent;
import com.stericycle.cordova.plugin.honeywell.json.model.JsonRFIDStatusEvent;
import com.stericycle.cordova.plugin.honeywell.json.model.JsonRFIDTagReadEvent;

import org.json.JSONException;

public class ReadTagHandler extends AbstractPluginListener {
    private static String LOG_TAG = "com.dff.cordova.plugin.honeywell.barcode.ReadTagHandler";

    public void onReadTagEvent(TagReadData tag) {
        CordovaPluginLog.d(LOG_TAG, "read tag event: " + tag.getEpcHexStr());

        try {
            this.sendPluginResult(JsonRFIDTagReadEvent.toJson(tag));
        }
        catch(JSONException e) {
            this.sendPluginResult(e);
        }
        catch(Exception e) {
            this.sendPluginResult(e);
        }
    }
}