package com.stericycle.cordova.plugin.honeywell.json.model;
import com.stericycle.cordova.plugin.honeywell.common.RFIDConnectionStatus;
import com.stericycle.cordova.plugin.honeywell.common.RFIDStatusEvent;

import org.json.JSONException;
import org.json.JSONObject;


public class JsonRFIDConnectionStatus {
    public static JSONObject toJson(RFIDConnectionStatus status) throws JSONException {
        JSONObject jsonEvent = new JSONObject();

        if (status != null) {
            jsonEvent.put("deviceConnected", status.DeviceConnected);
            jsonEvent.put("readerCreated", status.ReaderCreated);
            jsonEvent.put("macAddress", status.MacAddress);
        }

        return jsonEvent;
    }
}

