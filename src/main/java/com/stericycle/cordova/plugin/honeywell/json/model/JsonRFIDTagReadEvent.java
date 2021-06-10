package com.stericycle.cordova.plugin.honeywell.json.model;
import com.honeywell.rfidservice.rfid.TagReadData;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonRFIDTagReadEvent {
    public static JSONObject toJson(TagReadData tag) throws JSONException {
        JSONObject jsonEvent = new JSONObject();

        if (tag != null) {
            jsonEvent.put("antenna",tag.getAntenna());
            jsonEvent.put("frequency",tag.getFrequency());
            jsonEvent.put("readCount",tag.getReadCount());
            jsonEvent.put("hextstr",tag.getEpcHexStr());
            jsonEvent.put("rssi",tag.getRssi());
            jsonEvent.put("time",tag.getTime().toString());
        }

        return jsonEvent;
    }
}