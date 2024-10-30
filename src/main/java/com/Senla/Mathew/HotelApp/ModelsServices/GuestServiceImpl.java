package com.Senla.Mathew.HotelApp.ModelsServices;

import com.Senla.Mathew.HotelApp.Comparators.GuestAlphabetComparator;
import com.Senla.Mathew.HotelApp.Comparators.GuestCheckOutComparator;
import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.DTO.Guest.GuestMapper;
import com.Senla.Mathew.HotelApp.DTO.Service.ServiceDto;
import com.Senla.Mathew.HotelApp.DTO.Service.ServiceMapper;
import com.Senla.Mathew.HotelApp.Models.Guest;
import com.Senla.Mathew.HotelApp.Models.Service;
import com.Senla.Mathew.HotelApp.ModelsRepositories.GuestRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.ServiceRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class GuestServiceImpl {

    private final GuestRepository guestRepository;
    private final ServiceRepository serviceRepository;
    private final GuestMapper guestMapper;
    private final ServiceMapper serviceMapper;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, ServiceRepository serviceRepository,
                            GuestMapper guestMapper,ServiceMapper serviceMapper) {
        this.guestRepository = guestRepository;
        this.serviceRepository = serviceRepository;
        this.guestMapper = guestMapper;
        this.serviceMapper = serviceMapper;
    }

    public GuestDto getGuest(Long guestId){
        Guest guest = guestRepository.getById(guestId);
        GuestDto guestDto = guestMapper.toDto(guest);
        return guestDto;
    }

    @Transactional
    public void createGuest(GuestDto guestDto) {
        if (guestDto != null){
            Guest guest = guestMapper.toEntity(guestDto);
            guestRepository.save(guest);
        }
    }

    public List<GuestDto> showGuests(String sortBy) {
        List<GuestDto> guestDtos = guestMapper.toDtoList(guestRepository.findAll());
        return sortedList(guestDtos, sortBy);
    }

    public List<GuestDto> showUninhabitedGuests(String sortBy) {
        List<GuestDto> guestDtos = guestMapper.toDtoList(guestRepository.findUninhabitedGuests());
        return sortedList(guestDtos,sortBy);
    }

    @Transactional
    public void addService(ServiceDto serviceDto, Long guestId) {
        Guest guest = guestRepository.getById(guestId);
        Service service = serviceMapper.toEntity(serviceDto);
        service.setGuest(guest);
        serviceRepository.save(service);
    }

    private List<GuestDto> sortedList(List<GuestDto> guestDtos, String sortBy){
        switch (sortBy){
            case "alphabet" :
                Collections.sort(guestDtos, new GuestAlphabetComparator());
                return guestDtos;
            case "checkout" :
                Collections.sort(guestDtos, new GuestCheckOutComparator());
                return guestDtos;
            default: return guestDtos;
        }
    }
}

