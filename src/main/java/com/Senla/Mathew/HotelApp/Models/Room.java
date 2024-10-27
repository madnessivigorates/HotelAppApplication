package com.Senla.Mathew.HotelApp.Models;


import com.Senla.Mathew.HotelApp.Enum.Status;
import jakarta.persistence.*;
import com.Senla.Mathew.HotelApp.Enum.Status;


import java.sql.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "rooms", schema = "hotelsql")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "roomCost", nullable = false)
    private int roomCost;

    @Column(name = "roomNumber")
    private int roomNumber;

    @Column(name = "rating")
    private float rating;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "category", length = 25)
    private String category;

    @Column(name = "willBeFree")
    private LocalDate willBeFree;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Guest> listOfGuests = new ArrayList<>();

    public Room() {
    }

    public Room(RoomBuilder builder) {
        this.roomCost = builder.roomCost;
        this.roomNumber = builder.roomNumber;
        this.rating = builder.rating;
        this.capacity = builder.capacity;
        this.category = builder.category;
    }

    public static class RoomBuilder {
        private int roomCost;
        private int roomNumber;
        private float rating;
        private int capacity;
        private String category;

        public RoomBuilder roomCost(int roomCost) {
            this.roomCost = roomCost;
            return this;
        }

        public RoomBuilder roomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public RoomBuilder rating(float rating) {
            this.rating = rating;
            return this;
        }

        public RoomBuilder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public RoomBuilder category(String category) {
            this.category = category;
            return this;
        }

        public Room build() throws SQLException {
            return new Room(this);
        }
    }
    public void checkStatus(){
        if (listOfGuests.isEmpty()){
            this.status = Status.FREE;
            this.willBeFree = LocalDate.now();}
        else {
            this.status = Status.OCCUPIDE;
            this.willBeFree = listOfGuests.stream()
                    .map(Guest::getCheckOutDate)
                    .filter(date -> date != null)
                    .max(LocalDate::compareTo)
                    .orElse(null);
        }
    }

    public void addGuest(LocalDate checkoutDate) {
        if (checkoutDate.isAfter(willBeFree)) {this.willBeFree = checkoutDate;}
        this.status = Status.OCCUPIDE;
    }

    public void removeGuest(Guest guest) {
        listOfGuests.remove(guest);
        checkStatus();
    }

    public void setWillBeFree(LocalDate willBeFree) {
        this.willBeFree = willBeFree;
    }

    public void setListOfGuests(List<Guest> listOfGuests) {
        this.listOfGuests = listOfGuests;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public void setRoomCost(int roomCost) {
        this.roomCost = roomCost;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public List<Guest> getListOfGuests() {
        return listOfGuests;
    }

    public String getStatus() {
        return this.status.getStatusValue();
    }

    public int getRoomCost() {
        return roomCost;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getWillBeFree() {
        return willBeFree;
    }

    public String getCategory() {
        return category;
    }

    public float getRating() {
        return rating;
    }

    public int getCapacity() {
        return capacity;
    }
}
