package com.Senla.Mathew.HotelApp.ModelsServices;

import com.Senla.Mathew.HotelApp.Comparators.*;
import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.DTO.GuestHistory.GuestHistoryDto;
import com.Senla.Mathew.HotelApp.DTO.GuestHistory.GuestHistoryMapper;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomDto;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomMaper;
import com.Senla.Mathew.HotelApp.Enum.Status;
import com.Senla.Mathew.HotelApp.Models.Guest;
import com.Senla.Mathew.HotelApp.Models.GuestHistory;
import com.Senla.Mathew.HotelApp.Models.Room;
import com.Senla.Mathew.HotelApp.ModelsRepositories.GuestHistoryRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.GuestRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.RoomRepository;
import com.Senla.Mathew.HotelApp.ModelsServices.Interfaces.RoomService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final RoomMaper roomMaper;
    private final GuestHistoryRepository guestHistoryRepository;
    private final GuestHistoryMapper guestHistoryMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, GuestRepository guestRepository,
                           GuestHistoryRepository guestHistoryRepository,
                           RoomMaper roomMaper,
                           GuestHistoryMapper guestHistoryMapper) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.guestHistoryRepository = guestHistoryRepository;
        this.roomMaper = roomMaper;
        this.guestHistoryMapper = guestHistoryMapper;
    }

    public List<RoomDto> showFreeRooms(String sortBy) {
        List<RoomDto> roomDtos = roomMaper.toDTOList(roomRepository.findFreeRooms());
        return sortedList(roomDtos,sortBy);
    }

    public List<RoomDto> showAllRooms(String sortBy) {
        List<RoomDto> roomDtos = roomMaper.toDTOList(roomRepository.findAll());
        return sortedList(roomDtos,sortBy);
    }

    public List<RoomDto> showRoomsForDate(LocalDate lookingDate) {
        return roomMaper.toDTOList(roomRepository.findRoomsByAvailableDate(lookingDate));
    }

    public List<GuestHistoryDto> getHistoryOfLivers(Long idRoom) {
        return guestHistoryMapper.toDTOList(guestHistoryRepository.historyOfLivers(idRoom));
    }

    public RoomDto getRoom(Long idRoom){
        return roomMaper.toDto(roomRepository.getById(idRoom));
    }

    public void editStatus(Long idRoom, Status status) {
        Room room = roomRepository.getById(idRoom);
        room.setStatus(status);
        roomRepository.save(room);
    }

    @Transactional
    public void evictFromRoom(Long idGuest) {
        Guest guest = guestRepository.getById(idGuest);
        Room room = guest.getRoom();
        if (room != null) {
            GuestHistory guestHistory = new GuestHistory(guest);
            guestHistoryRepository.save(guestHistory);
            room.removeGuest(guest);
            roomRepository.save(room);
        } else {
            throw new IllegalArgumentException("Гость не привязан к комнате.");
        }
    }

    @Transactional
    public void putInRoom(Long idRoom, Long idGuest, LocalDate checkoutDate) {
        Guest guest = guestRepository.getById(idGuest);
        Room room = roomRepository.getById(idRoom);
        guest.setCheckInDate(LocalDate.now());
        guest.setCheckOutDate(checkoutDate);
        guest.setRoom(room);
        guestRepository.save(guest);
        room.addGuest(checkoutDate);
        roomRepository.save(room);
    }

    private List<RoomDto> sortedList(List<RoomDto> roomDtos, String sortBy){
        switch (sortBy){
            case "capacity" :
                Collections.sort(roomDtos, new RoomCapacityComparator());
                return roomDtos;
            case "category" :
                Collections.sort(roomDtos, new RoomCategoryComparator());
                return roomDtos;
            case "price":
                Collections.sort(roomDtos, new RoomPriceComparator());
                return roomDtos;
            case "rank":
                Collections.sort(roomDtos,new RoomRankComparator());
                return roomDtos;
            default: return roomDtos;
        }
    }
}
