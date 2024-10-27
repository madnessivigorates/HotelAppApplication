package com.Senla.Mathew.HotelApp.DTO.Guest;

import com.Senla.Mathew.HotelApp.DTO.Service.ServiceDto;

import java.time.LocalDate;
import java.util.List;

public class GuestDto {
    private Long idGuest;
    private Long idRoom;
    private String name;
    private String surname;
    private int age;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private List<ServiceDto> serviceDtoList;

    public GuestDto() {}

    public GuestDto(Long id, String name, String surname, int age) {
        this.idGuest = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Long getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Long guestId) {
        this.idGuest = guestId;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public Long getIdRoom(){
        return idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<ServiceDto> getServiceDtoList() {
        return serviceDtoList;
    }

    public void setServiceDtoList(List<ServiceDto> services) {
        this.serviceDtoList = services;
    }

}
