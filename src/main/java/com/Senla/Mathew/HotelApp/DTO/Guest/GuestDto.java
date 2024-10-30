package com.Senla.Mathew.HotelApp.DTO.Guest;

import com.Senla.Mathew.HotelApp.DTO.Service.ServiceDto;

import java.time.LocalDate;
import java.util.List;

public record GuestDto(Long idGuest,Long idRoom,String name,String surname,int age,LocalDate checkInDate,LocalDate checkOutDate,List<ServiceDto> serviceDtoList) {

}
