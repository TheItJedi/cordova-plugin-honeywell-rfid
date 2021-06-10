package com.stericycle.cordova.plugin.honeywell.rfid;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.stericycle.cordova.plugin.honeywell.common.RFIDStatusEvent;
import com.stericycle.cordova.plugin.honeywell.json.model.JsonRFIDStatusEvent;
import org.json.JSONException;

public class RFIDStatusHandler extends AbstractPluginListener {
	private static String LOG_TAG = "com.dff.cordova.plugin.honeywell.barcode.RFIDStatusHandler";
	
	public void onRFIDStatusEvent(RFIDStatusEvent event) {
		CordovaPluginLog.d(LOG_TAG, "rfid status event: " + event.EventMessage);
		
		try {
			this.sendPluginResult(JsonRFIDStatusEvent.toJson(event));
		}
		catch(JSONException e) {
			this.sendPluginResult(e);
		}
		catch(Exception e) {
			this.sendPluginResult(e);
		}
	}
}