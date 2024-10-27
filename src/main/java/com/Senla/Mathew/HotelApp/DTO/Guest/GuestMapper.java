package com.Senla.Mathew.HotelApp.DTO.Guest;

import com.Senla.Mathew.HotelApp.DTO.Service.ServiceMapper;
import com.Senla.Mathew.HotelApp.Models.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ServiceMapper.class})
public interface GuestMapper {
    @Mapping(source = "room.idRoom", target = "idRoom")
    @Mapping(source = "servicesList", target = "serviceDtoList")
    @Mapping(source = "idGuest", target = "idGuest")
    GuestDto toDTO(Guest guest);

    @Mapping(target = "idGuest", ignore = true)
    Guest toEntity(GuestDto guestDto);

    List<GuestDto> toDTOList(List<Guest> guests);
    }
