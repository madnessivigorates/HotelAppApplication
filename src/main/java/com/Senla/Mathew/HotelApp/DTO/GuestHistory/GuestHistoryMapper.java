package com.Senla.Mathew.HotelApp.DTO.GuestHistory;

import com.Senla.Mathew.HotelApp.Models.GuestHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestHistoryMapper {
    GuestHistoryDto toDto(GuestHistory guestHistory);
    GuestHistory toEntity(GuestHistoryDto guestHistoryDto);
    List<GuestHistoryDto> toDTOList(List<GuestHistory> guestHistoryList);
}
