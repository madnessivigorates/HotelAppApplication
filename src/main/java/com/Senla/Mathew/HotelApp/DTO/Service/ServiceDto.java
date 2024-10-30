package com.Senla.Mathew.HotelApp.DTO.Service;

import java.time.LocalDate;

public record ServiceDto(Long idService,String name,int price,LocalDate date,Long idGuest) {

}
