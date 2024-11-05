package com.Senla.Mathew.HotelApp.ModelsServices;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.DTO.Guest.GuestMapper;
import com.Senla.Mathew.HotelApp.DTO.GuestHistory.GuestHistoryDto;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomDto;
import com.Senla.Mathew.HotelApp.Enum.Status;
import com.Senla.Mathew.HotelApp.Models.Guest;
import com.Senla.Mathew.HotelApp.Models.GuestHistory;
import com.Senla.Mathew.HotelApp.Models.Room;
import com.Senla.Mathew.HotelApp.ModelsRepositories.GuestHistoryRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.GuestRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.RoomRepository;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomMaper;
import com.Senla.Mathew.HotelApp.DTO.GuestHistory.GuestHistoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private GuestHistoryRepository guestHistoryRepository;

    @Mock
    private RoomMaper roomMaper;

    @Mock
    private GuestMapper guestMapper;

    @Mock
    private GuestHistoryMapper guestHistoryMapper;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room room;
    private Guest guest;
    private RoomDto roomDto;
    private GuestHistory guestHistory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        guest =  new Guest.GuestBuilder()
                .name("Марк")
                .surname("Линкевич")
                .age(23)
                .build();
        guest.setId(1L);

        room = new Room.RoomBuilder()
                .capacity(2)
                .roomCost(1500)
                .rating(5f)
                .category("Люкс")
                .roomNumber(15)
                .build();
        room.setId(1L);
        room.setStatus(Status.FREE);
        room.setWillBeFree(LocalDate.of(2000,01,01));

        roomDto = new RoomDto(1L,"Свободно",1500,15,5f,2,"Люкс",
                LocalDate.of(2024,11,01), Collections.emptyList());
        when(roomMaper.toDtoList(anyList())).thenReturn(Collections.singletonList(roomDto));
    }

    @Test
    public void testShowFreeRooms() {
        // Arrange
        when(roomRepository.findFreeRooms()).thenReturn(Collections.singletonList(room));
        // Act
        List<RoomDto> freeRooms = roomService.showFreeRooms("capacity");
        // Assert
        assertNotNull(freeRooms);
        assertFalse(freeRooms.isEmpty());
        assertEquals(1, freeRooms.size());
        assertEquals(roomDto, freeRooms.get(0));
    }

    @Test
    public void testShowAllRooms() {
        // Arrange
        when(roomRepository.findAll()).thenReturn(Collections.singletonList(room));
        // Act
        List<RoomDto> allRooms = roomService.showAllRooms("capacity");
        // Assert
        assertNotNull(allRooms);
        assertFalse(allRooms.isEmpty());
        assertEquals(1, allRooms.size());
        assertEquals(roomDto, allRooms.get(0));
    }

    @Test
    public void testShowRoomsForDate() {
        // Arrange
        LocalDate date = LocalDate.now();
        when(roomRepository.findRoomsByAvailableDate(date)).thenReturn(Collections.singletonList(room));
        // Act
        List<RoomDto> roomsForDate = roomService.showRoomsForDate(LocalDate.now());
        // Assert
        assertNotNull(roomsForDate);
        assertFalse(roomsForDate.isEmpty());
        assertEquals(1, roomsForDate.size());
        assertEquals(roomDto, roomsForDate.get(0));
    }

    @Test
    public void testGetHistoryOfLivers() {
        // Arrange
        when(guestHistoryRepository.historyOfLivers(1L)).thenReturn(Collections.emptyList());
        when(guestHistoryMapper.toDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());
        // Act
        List<GuestHistoryDto> result = roomService.getHistoryOfLivers(1L);
        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRoom() {
        // Arrange
        when(roomRepository.getById(1L)).thenReturn(room);
        when(roomMaper.toDto(any())).thenReturn(roomDto);
        // Act
        RoomDto result = roomService.getRoom(1L);
        // Assert
        assertNotNull(result);
        assertEquals(room.getId(), result.idRoom());
    }

    @Test
    public void testEditStatus() {
        // Arrange
        when(roomRepository.getById(1L)).thenReturn(room);
        // Act
        roomService.editStatus(1L, Status.OCCUPIDE);
        // Assert
        assertEquals(Status.OCCUPIDE.getStatusValue(), room.getStatus());
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    public void testEvictFromRoom() {
        // Arrange
        guest.setRoom(room);
        when(guestRepository.getById(1L)).thenReturn(guest);
        when(roomRepository.getById(1L)).thenReturn(room);
        // Act
        roomService.evictFromRoom(1L);
        // Assert
        assertFalse(room.getListOfGuests().contains(guest));
        verify(guestHistoryRepository, times(1)).save(any());
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    public void testPutInRoom() {
        // Arrange
        LocalDate checkoutDate = LocalDate.of(2024,11,10);
        when(guestRepository.getById(1L)).thenReturn(guest);
        when(roomRepository.getById(1L)).thenReturn(room);
        // Act
        roomService.putInRoom(1L, 1L, checkoutDate);
        // Assert
        assertEquals(checkoutDate, guest.getCheckOutDate());
        assertEquals(LocalDate.now(), guest.getCheckInDate());
        assertEquals(room, guest.getRoom());
        verify(guestRepository, times(1)).save(guest);
        verify(roomRepository, times(1)).save(room);
    }

}