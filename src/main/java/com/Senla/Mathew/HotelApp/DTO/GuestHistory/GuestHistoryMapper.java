package com.Senla.Mathew.HotelApp.DTO.GuestHistory;

import com.Senla.Mathew.HotelApp.Models.GuestHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestHistoryMapper {
    @Mapping(source = "id", target = "idGuestHistory")
    @Mapping(source = "room.id", target = "idRoom")
    GuestHistoryDto toDto(GuestHistory guestHistory);

    GuestHistory toEntity(GuestHistoryDto guestHistoryDto);

    List<GuestHistoryDto> toDtoList(List<GuestHistory> guestHistoryList);
}
