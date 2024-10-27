package com.Senla.Mathew.HotelApp.DTO.Service;

import com.Senla.Mathew.HotelApp.Models.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    @Mapping(source = "guest.idGuest", target = "idGuest")
    ServiceDto toDTO(Service service);

    Service toEntity(ServiceDto serviceDto);
}
