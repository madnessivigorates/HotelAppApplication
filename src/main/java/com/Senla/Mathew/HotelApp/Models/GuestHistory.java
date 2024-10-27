package com.Senla.Mathew.HotelApp.Models;

import jakarta.persistence.*;


import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "guesthistory", schema = "hotelsql")
public class GuestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGuestHistory;

    @Column(name = "idRoom")
    private Long idRoom;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "surname", nullable = false, length = 25)
    private String surname;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "checkInDate")
    private LocalDate checkInDate;

    @Column(name = "checkOutDate")
    private LocalDate checkOutDate;

    public GuestHistory() {}

    public GuestHistory(Guest guest) {
        this.name = guest.getName();
        this.surname = guest.getSurname();
        this.age = guest.getAge();
        this.checkInDate = guest.getCheckInDate();
        this.checkOutDate = guest.getCheckOutDate();
        this.idRoom = guest.getRoom() != null ? guest.getRoom().getIdRoom() : 0;
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

    @Override
    public String toString() {
        return "GuestHistory{" +
                ", idRoom=" + idRoom +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}