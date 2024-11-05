package com.Senla.Mathew.HotelApp.ModelsControllers;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.DTO.Guest.GuestMapper;
import com.Senla.Mathew.HotelApp.DTO.Service.ServiceDto;
import com.Senla.Mathew.HotelApp.Models.Guest;
import com.Senla.Mathew.HotelApp.Models.Service;
import com.Senla.Mathew.HotelApp.ModelsServices.GuestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GuestDataControllerTest {

    @Mock
    private GuestServiceImpl guestService;

    @Mock
    private GuestMapper guestMapper;

    @InjectMocks
    private GuestDataController guestDataController;

    private Guest guest;
    private GuestDto guestDto;
    private Service service;

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
    }

    @Test
    public void testCreateGuest() {
        // Arrange
        guestDataController.createGuest(guest);

        // Assert
        verify(guestService, times(1)).createGuest(guest);
    }

    @Test
    public void testAddService() {

        // Act
        guestDataController.addService(service, 1L);

        // Assert
        verify(guestService, times(1)).addService(service, 1L);
    }

    @Test
    public void testShowGuests() {
        // Arrange
        String sortBy = "name";

        when(guestService.showGuests(sortBy)).thenReturn(Collections.singletonList(guestDto));

        // Act
        List<GuestDto> result = guestDataController.showGuests(sortBy);

        // Assert
        verify(guestService, times(1)).showGuests(sortBy);
        assertEquals(guestDto, result.get(0));
    }

    @Test
    public void testShowUninhabitedGuests() {
        // Arrange
        String sortBy = "name";
        when(guestService.showUninhabitedGuests(sortBy)).thenReturn(Collections.singletonList(guestDto));

        // Act
        List<GuestDto> result = guestDataController.showUninhabitedGuests(sortBy);

        // Assert
        verify(guestService, times(1)).showUninhabitedGuests(sortBy);
        assertEquals(guestDto, result.get(0));
    }

    @Test
    public void testGetGuest() {
        // Arrange
        when(guestService.getGuest(1L)).thenReturn(guestDto);

        // Act
        GuestDto result = guestDataController.getGuest(1L);

        // Assert
        verify(guestService, times(1)).getGuest(1L);
        assertEquals(guestDto, result);
    }
}