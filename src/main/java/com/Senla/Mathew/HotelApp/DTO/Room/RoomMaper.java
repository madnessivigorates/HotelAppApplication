package com.Senla.Mathew.HotelApp.DTO.Room;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestMapper;
import com.Senla.Mathew.HotelApp.Models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GuestMapper.class})
public interface RoomMaper {
    @Mapping(source = "id",target = "idRoom")
    @Mapping(source = "listOfGuests",target = "guestsDtoList")
    RoomDto toDto(Room room);

    Room toEntity(RoomDto roomDto);

    List<RoomDto> toDtoList(List<Room> guests);
}
