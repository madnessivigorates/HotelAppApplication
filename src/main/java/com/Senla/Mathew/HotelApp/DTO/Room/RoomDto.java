package com.Senla.Mathew.HotelApp.DTO.Room;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.Enum.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomDto {

    private Long idRoom;
    private String status;
    private int roomCost;
    private int roomNumber;
    private float rating;
    private int capacity;
    private String category;
    private LocalDate willBeFree;
    private List<GuestDto> guestsDtoList = new ArrayList<>();

    public RoomDto() {
    }

    public RoomDto(Long idRoom, String status, int roomCost, int roomNumber, float rating, int capacity, String category, LocalDate willBeFree) {
        this.idRoom = idRoom;
        this.status = status;
        this.roomCost = roomCost;
        this.roomNumber = roomNumber;
        this.rating = rating;
        this.capacity = capacity;
        this.category = category;
        this.willBeFree = willBeFree;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomCost() {
        return roomCost;
    }

    public void setRoomCost(int roomCost) {
        this.roomCost = roomCost;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getWillBeFree() {
        return willBeFree;
    }

    public void setWillBeFree(LocalDate willBeFree) {
        this.willBeFree = willBeFree;
    }

    public List<GuestDto> getGuestsDtoList() {
        return guestsDtoList;
    }

    public void setGuestsDtoList(List<GuestDto> listOfGuests) {
        this.guestsDtoList = listOfGuests;
    }
}
