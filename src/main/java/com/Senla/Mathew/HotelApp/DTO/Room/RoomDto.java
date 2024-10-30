package com.Senla.Mathew.HotelApp.DTO.Room;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;

import java.time.LocalDate;
import java.util.List;

public record RoomDto(Long idRoom,String status,int roomCost,int roomNumber,float rating,int capacity,String category,LocalDate willBeFree,List<GuestDto> guestsDtoList) {

}
