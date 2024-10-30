package com.Senla.Mathew.HotelApp.DTO.GuestHistory;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record GuestHistoryDto(Long idGuestHistory,Long idRoom,String name,String surname,int age,LocalDate checkInDate,LocalDate checkOutDate) {
}
