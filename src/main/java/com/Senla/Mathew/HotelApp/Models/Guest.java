package com.Senla.Mathew.HotelApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "guests", schema = "hotelsql")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGuest")
    private Long idGuest;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "surname", nullable = false, length = 25)
    private String surname;

    @Column(name = "age", nullable = false)
    private int age;

    @Transient
    private int guestRoomNumber = 0;

    @Column(name = "checkInDate")
    private LocalDate checkInDate;

    @Column(name = "checkOutDate")
    private LocalDate checkOutDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoom", nullable = true)
    private Room room ;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> servicesList;


    public Guest() {
    }

    public Guest(GuestBuilder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
    }

    public static class GuestBuilder {
        private String name;
        private String surname;
        private int age;

        public GuestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GuestBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public GuestBuilder age(int age) {
            this.age = age;
            return this;
        }

        public Guest build() {
            return new Guest(this);
        }
    }

    @PostLoad
    public void init() {
        if (room != null) {
            guestRoomNumber = room.getRoomNumber();
        }
    }

    public Long getIdRoom() {
        return room.getIdRoom();
    }

    public List<Service> getServicesList() {
        return this.servicesList;
    }

    public void setServicesList(List<Service> servicesList) {
        this.servicesList = servicesList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdGuest(Long idGuest) {
        this.idGuest = idGuest;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setGuestRoomNumber(int guestRoomNumber) {
        this.guestRoomNumber = guestRoomNumber;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public Long getIdGuest() {
        return idGuest;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getGuestRoomNumber() {
        return guestRoomNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void sortServicesByPrice() {
        servicesList.sort(Comparator.comparingDouble(Service::getPrice));
    }

    // Сортировка услуг по дате
    public void sortServicesByDate() {
        servicesList.sort(Comparator.comparing(Service::getDate));
    }

    @Override
    public String toString() {
        return "GuestID: " + idGuest +
                " Имя: " + name +
                " Фамилия: " + surname +
                " Возраст: " + age +
                " Номер: " + guestRoomNumber +
                " Заселение: " + checkInDate +
                " Выезд: " + checkOutDate +
                " Услуги: " + servicesList + "\n";
    }
}
