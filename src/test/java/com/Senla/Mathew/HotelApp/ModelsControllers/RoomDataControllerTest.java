package com.Senla.Mathew.HotelApp.ModelsControllers;

import com.Senla.Mathew.HotelApp.DTO.GuestHistory.GuestHistoryDto;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomDto;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomMaper;
import com.Senla.Mathew.HotelApp.Enum.Status;
import com.Senla.Mathew.HotelApp.Models.Room;
import com.Senla.Mathew.HotelApp.ModelsServices.RoomServiceImpl;
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
import static org.mockito.Mockito.*;

public class RoomDataControllerTest {

    @Mock
    private RoomServiceImpl roomService;

    @InjectMocks
    private RoomDataController roomDataController;

    private Room room;
    private RoomDto roomDto;
    private GuestHistoryDto guestHistoryDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        room = new Room.RoomBuilder()
                .capacity(2)
                .roomCost(1500)
                .rating(5f)
                .category("Люкс")
                .roomNumber(15)
                .build();
        room.setId(1L);
        room.setStatus(Status.FREE);

        roomDto = new RoomDto(1L,"Свободно",1500,15,5f,2,"Люкс",
                LocalDate.of(2024,11,01), Collections.emptyList());
        guestHistoryDto = new GuestHistoryDto(1L,1L,"Антон", "Чехов",45,
                LocalDate.of(2024,10,20),LocalDate.of(2024,10,25));
    }

    @Test
    public void testShowFreeRooms() {
        // Arrange
        String sortBy = "name";
        when(roomService.showFreeRooms(sortBy)).thenReturn(Collections.singletonList(roomDto));
        // Act
        List<RoomDto> result = roomDataController.showFreeRooms(sortBy);

        // Assert
        assertEquals(roomDto, result.get(0));
    }

    @Test
    public void testShowAllRooms() {
        // Arrange
        String sortBy = "name";
        when(roomService.showAllRooms(sortBy)).thenReturn(Collections.singletonList(roomDto));

        // Act
        List<RoomDto> result = roomDataController.showAllRooms(sortBy);

        // Assert
        assertEquals(roomDto, result.get(0));
    }

    @Test
    public void testShowRoomsForDate() {
        // Arrange
        when(roomService.showRoomsForDate(LocalDate.now())).thenReturn(Collections.singletonList(roomDto));

        // Act
        List<RoomDto> result = roomDataController.showRoomsForDate(LocalDate.now());

        // Assert
        assertEquals(roomDto, result.get(0));
    }

    @Test
    public void testGetRoom() {
        // Arrange
        when(roomService.getRoom(1L)).thenReturn(roomDto);

        // Act
        RoomDto result = roomDataController.getRoom(1L);

        // Assert
        assertEquals(roomDto, result);
    }

    @Test
    public void testGetHistoryOfLivers() {
        // Arrange
        when(roomService.getHistoryOfLivers(1L)).thenReturn(Collections.singletonList(guestHistoryDto));

        // Act
        List<GuestHistoryDto> result = roomDataController.getHistoryOfLivers(1L);

        // Assert
        verify(roomService, times(1)).getHistoryOfLivers(1L);
        assertEquals(guestHistoryDto, result.get(0));
    }

    @Test
    public void testPutInRoom() {
        // Arrange
        Long roomId = 1L;
        Long guestId = 2L;
        LocalDate checkoutDate = LocalDate.now().plusDays(5);

        // Act
        roomDataController.putInRoom(roomId, guestId, checkoutDate);

        // Assert
        verify(roomService, times(1)).putInRoom(roomId, guestId, checkoutDate);
    }

    @Test
    public void testEditStatus() {
        // Arrange
        Long roomId = 1L;
        Status status = Status.OCCUPIDE;

        // Act
        roomDataController.editStatus(roomId, status);

        // Assert
        verify(roomService, times(1)).editStatus(roomId, status);
    }

    @Test
    public void testEvictFromRoom() {
        // Arrange
        Long guestId = 1L;
        // Act
        roomDataController.evictFromRoom(guestId);

        // Assert
        verify(roomService, times(1)).evictFromRoom(guestId);
    }
}
