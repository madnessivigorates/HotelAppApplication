package com.Senla.Mathew.HotelApp.Enum;

public enum Status {
    OCCUPIDE("Занято"),
    REPAIR("Ремонт"),
    CLEANING("Уборка"),
    FREE("Свободно");

    private String status;
    Status(String status) {
        this.status = status;
    }

    public String getStatusValue() {
        return status;
    }
}
