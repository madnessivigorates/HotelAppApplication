package com.Senla.Mathew.HotelApp.Models;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "guest_history", schema = "hotelsql")
public class GuestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGuestHistory")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom")
    private Room room ;

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
        this.room = guest.getRoom() != null ? guest.getRoom() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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