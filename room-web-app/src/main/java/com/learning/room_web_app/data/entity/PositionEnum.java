package com.learning.room_web_app.data.entity;

public enum PositionEnum {
    HOUSEKEEPING,
    SECURITY,
    FRONT_DESK,
    CONCIERGE;

    public String toString() {
        switch(this) {
            case HOUSEKEEPING -> {
                return "Housekeeping";
            }
            case SECURITY -> {
                return "Security";
            }
            case FRONT_DESK -> {
                return "Front Desk";
            }
            case CONCIERGE -> {
                return "Concierge";
            }
        }
        return "";
    }
}
