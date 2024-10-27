package com.Senla.Mathew.HotelApp.DTO.Service;

import java.time.LocalDate;

public class ServiceDto {
    private Long idService;
    private String name;
    private int price;
    private LocalDate date;
    private Long idGuest;

    public ServiceDto() {
    }

    public ServiceDto(Long idService, String name, int price, LocalDate date, Long idGuest) {
        this.idService = idService;
        this.name = name;
        this.price = price;
        this.date = date;
        this.idGuest = idGuest;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Long idGuest) {
        this.idGuest = idGuest;
    }
}
