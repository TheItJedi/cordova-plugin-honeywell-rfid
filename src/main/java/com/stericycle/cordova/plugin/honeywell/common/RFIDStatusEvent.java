package com.stericycle.cordova.plugin.honeywell.common;

public class RFIDStatusEvent {
    public enum EventType{
        MANAGERCREATED(0),
        DEVICECONNECTED(1),
        READERCREATED(2),
        READERTRIGGERED(3),
        STARTREAD(4),
        STOPREAD(5),
        READTAG(6);


        private final int value;

        EventType(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }
    public static RFIDStatusEvent ManagerCreated(){
        RFIDStatusEvent event = new RFIDStatusEvent();
        event.StatusEventType = RFIDStatusEvent.EventType.MANAGERCREATED;
        event.EventMessage = "RFIDManager Created";
        return event;
    }
    public static RFIDStatusEvent DeviceConnectedEvent(String macAddress){
        RFIDStatusEvent event = new RFIDStatusEvent();
        event.StatusEventType = RFIDStatusEvent.EventType.DEVICECONNECTED;
        event.EventMessage = "Mac Address " + macAddress;
        return event;
    }
    public static RFIDStatusEvent ReadTagEvent(String tag){
        RFIDStatusEvent event = new RFIDStatusEvent();
        event.StatusEventType = EventType.READTAG;
        event.EventMessage = "Tag  " + tag;
        return event;
    }
    public static RFIDStatusEvent StartStopRead(boolean reading){
        RFIDStatusEvent event = new RFIDStatusEvent();
        if(reading){
            event.StatusEventType = EventType.STARTREAD;
            event.EventMessage = "STARTING TO READ";
        }
        else{
            event.StatusEventType = EventType.STOPREAD;
            event.EventMessage = "STOPPING READ";

        }
        return event;
    }
    public static RFIDStatusEvent ReaderCreatedEvent(){
        RFIDStatusEvent event = new RFIDStatusEvent();
        event.StatusEventType = RFIDStatusEvent.EventType.READERCREATED;
        event.EventMessage = "RFID Reader Created";
        return event;
    }
    public static RFIDStatusEvent RFIDReaderTriggered(boolean b){
        RFIDStatusEvent event = new RFIDStatusEvent();
        event.StatusEventType = RFIDStatusEvent.EventType.READERTRIGGERED;
        if(b){
            event.EventMessage = "RFID Reader Triggered TRUE";
        }
        else {
            event.EventMessage = "RFID Reader Triggered False";
        }
        return event;
    }
    public EventType StatusEventType;
    public String EventMessage;
    
}

