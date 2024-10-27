package com.Senla.Mathew.HotelApp.DTO.GuestHistory;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class GuestHistoryDto {
    private Long idGuestHistory;
    private Long idRoom;
    private String name;
    private String surname;
    private int age;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public GuestHistoryDto() {
    }

    public GuestHistoryDto(Long idRoom, String name, String surname, int age, LocalDate checkInDate, LocalDate checkOutDate) {
        this.idRoom = idRoom;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Long getIdGuestHistory() {
        return idGuestHistory;
    }

    public void setIdGuestHistory(Long idGuestHistory) {
        this.idGuestHistory = idGuestHistory;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
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
}
