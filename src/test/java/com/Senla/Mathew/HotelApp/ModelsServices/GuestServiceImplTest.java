package com.Senla.Mathew.HotelApp.ModelsServices;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.DTO.Guest.GuestMapper;
import com.Senla.Mathew.HotelApp.Models.Guest;
import com.Senla.Mathew.HotelApp.Models.Service;
import com.Senla.Mathew.HotelApp.ModelsRepositories.GuestRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GuestServiceImplTest {

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private GuestMapper guestMapper;

    private Guest guest;
    private GuestDto guestDto;
    private Service service;

    @InjectMocks
    private GuestServiceImpl guestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        guest = new Guest.GuestBuilder()
                .name("Вася")
                .surname("Пупкин")
                .age(22)
                .build();
        guest.setId(1L);
        guestDto = guestMapper.toDto(guest);
        service = new Service("Спа",1200, LocalDate.now());
        when(guestMapper.toDtoList(anyList())).thenReturn(Collections.singletonList(guestDto));
        when(guestMapper.toDto(guest)).thenReturn(guestDto);
    }

    @Test
    public void testGetGuest() {
        // Arrange
        when(guestRepository.getById(1L)).thenReturn(guest);
        // Act
        GuestDto result = guestService.getGuest(1L);
        // Assert
        verify(guestRepository, times(1)).getById(1L);
        verify(guestMapper, times(2)).toDto(guest);
        assertEquals(guestDto, result);
    }

    @Test
    public void testCreateGuest() {
        guestService.createGuest(guest);
        verify(guestRepository, times(1)).save(guest);
    }

    @Test
    public void testShowGuests() {
        // Arrange
        String sortBy = "name";
        when(guestRepository.findAll()).thenReturn(Collections.singletonList(guest));
        // Act
        List<GuestDto> result = guestService.showGuests(sortBy);
        // Assert
        verify(guestRepository, times(1)).findAll();
        verify(guestMapper, times(1)).toDtoList(anyList());
        assertNotNull(result);
        assertEquals(guestDto, result.get(0));
    }

    @Test
    public void testShowUninhabitedGuests() {
        // Arrange
        String sortBy = "checkout";
        when(guestRepository.findUninhabitedGuests()).thenReturn(Collections.singletonList(guest));
        when(guestMapper.toDtoList(anyList())).thenReturn(Collections.singletonList(guestDto));
        // Act
        List<GuestDto> result = guestService.showUninhabitedGuests(sortBy);
        // Assert
        verify(guestRepository, times(1)).findUninhabitedGuests();
        verify(guestMapper, times(1)).toDtoList(anyList());
        assertEquals(guestDto, result.get(0));
    }

    @Test
    public void testAddService() {
        // Arrange
        when(guestRepository.getById(1L)).thenReturn(guest);
        // Act
        guestService.addService(service, 1L);
        // Assert
        verify(guestRepository, times(1)).getById(1L);
        verify(serviceRepository, times(1)).save(service);
        assertEquals(guest, service.getGuest());
    }
}
