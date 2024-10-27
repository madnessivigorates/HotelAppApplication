package com.Senla.Mathew.HotelApp.ModelsControllers;

import com.Senla.Mathew.HotelApp.DTO.GuestHistory.GuestHistoryDto;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomDto;
import com.Senla.Mathew.HotelApp.Enum.Status;
import com.Senla.Mathew.HotelApp.ModelsServices.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomDataController {

    private RoomServiceImpl roomService;

    @Autowired
    public RoomDataController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/free")
    public List<RoomDto> showFreeRooms(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return roomService.showFreeRooms(sortBy);
    }

    @GetMapping()
    public List<RoomDto> showAllRooms(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return roomService.showAllRooms(sortBy);
    }
    @GetMapping("/for-date")
    public List<RoomDto> showRoomsForDate(@RequestParam("lookingDate") LocalDate lookingDate) {
        return roomService.showRoomsForDate(lookingDate);
    }

    @GetMapping("/room")
    public RoomDto getRoom(@RequestParam("idRoom") Long idRoom){
        return roomService.getRoom(idRoom);
    }

    @GetMapping("/room-history")
    public List<GuestHistoryDto> getHistoryOfLivers(@RequestParam("idRoom") Long idRoom) {
        return roomService.getHistoryOfLivers(idRoom);
    }

    @PutMapping("/put-in-room")
    public void putInRoom(@RequestParam("idRoom") Long idRoom,
                          @RequestParam("idGuest") Long idGuest,
                          @RequestParam("checkoutDate") LocalDate checkoutDate) {
        roomService.putInRoom(idRoom, idGuest, checkoutDate);
    }

    @PutMapping("/edit/room-status")
    public void editStatus(@RequestParam("idRoom") Long idRoom,
                           @RequestParam("status") Status status){
        roomService.editStatus(idRoom,status);
    }

    @DeleteMapping("/evict")
    public void evictFromRoom(@RequestParam("idGuest") Long idGuest) {
        roomService.evictFromRoom(idGuest);
    }
}
