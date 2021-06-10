package com.stericycle.cordova.plugin.honeywell.json.model;
import com.stericycle.cordova.plugin.honeywell.common.RFIDStatusEvent;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonRFIDStatusEvent {
	public static JSONObject toJson(RFIDStatusEvent event) throws JSONException {
		JSONObject jsonEvent = new JSONObject();
		
		if (event != null) {
			jsonEvent.put("eventType", event.StatusEventType.toString());
			jsonEvent.put("eventMessage", event.EventMessage);
		}
		
		return jsonEvent;
	}
}