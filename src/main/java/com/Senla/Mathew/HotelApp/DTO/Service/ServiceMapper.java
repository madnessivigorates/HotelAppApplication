package com.Senla.Mathew.HotelApp.DTO.Service;

import com.Senla.Mathew.HotelApp.Models.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    @Mapping(source = "guest.id", target = "idGuest")
    @Mapping(source = "id", target = "idService")
    ServiceDto toDto(Service service);

    Service toEntity(ServiceDto serviceDto);
}
