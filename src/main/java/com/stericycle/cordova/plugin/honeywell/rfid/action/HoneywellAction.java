package com.stericycle.cordova.plugin.honeywell.rfid.action;

import com.dff.cordova.plugin.common.action.CordovaAction;
import com.stericycle.cordova.plugin.honeywell.common.RFIDReaderManager;
import com.honeywell.rfidservice.RfidManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

public abstract class HoneywellAction extends CordovaAction {
	protected RFIDReaderManager readerManager;
	protected RfidManager rfidManager;

	// all return strings
	public static final String RFID_READER_NOT_INIT = "RFID reader not initialized";
	public static final String RFID_READER_INIT = "RFID reader initialized";
	public static final String RFID_CLOSED_SUCCESS = "RFID reader was closed successfully";
	public static final String NO_RFID_READER_CONNECTED = "No RFID reader is connected.";
	public static final String RFIDMGR_NOT_INIT = "RfidManager not initialized";
	public static final String RFID_READER_ALREADY_ADDED = "A RFID reader is already connected.";
	public static final String NO_CONNECTED_DEVICES = "No connected devices.";
	public static final String LOST_DEVICE_CONNECTION = "Lost connection to connected RFID reader.";
	public static final String PROPERTIES_SET = "All properties set.";

	public HoneywellAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
    RFIDReaderManager readerManager, RfidManager rfidManager) {
		super(action, args, callbackContext, cordova);

		this.readerManager = readerManager;
		this.rfidManager = rfidManager;
	}
}