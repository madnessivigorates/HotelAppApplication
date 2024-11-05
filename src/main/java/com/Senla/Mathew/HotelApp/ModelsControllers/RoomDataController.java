package com.Senla.Mathew.HotelApp.ModelsControllers;

import com.Senla.Mathew.HotelApp.DTO.GuestHistory.GuestHistoryDto;
import com.Senla.Mathew.HotelApp.DTO.Room.RoomDto;
import com.Senla.Mathew.HotelApp.Enum.Status;
import com.Senla.Mathew.HotelApp.ModelsServices.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/free")
    public List<RoomDto> showFreeRooms(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return roomService.showFreeRooms(sortBy);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<RoomDto> showAllRooms(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return roomService.showAllRooms(sortBy);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/for-date")
    public List<RoomDto> showRoomsForDate(@RequestParam("lookingDate") LocalDate lookingDate) {
        return roomService.showRoomsForDate(lookingDate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/room")
    public RoomDto getRoom(@RequestParam("idRoom") Long idRoom){
        return roomService.getRoom(idRoom);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/room-history")
    public List<GuestHistoryDto> getHistoryOfLivers(@RequestParam("idRoom") Long idRoom) {
        return roomService.getHistoryOfLivers(idRoom);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/put-in-room")
    public void putInRoom(@RequestParam("idRoom") Long idRoom,
                          @RequestParam("idGuest") Long idGuest,
                          @RequestParam("checkoutDate") LocalDate checkoutDate) {
        roomService.putInRoom(idRoom, idGuest, checkoutDate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/room-status")
    public void editStatus(@RequestParam("idRoom") Long idRoom,
                           @RequestParam("status") Status status){
        roomService.editStatus(idRoom,status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/evict")
    public void evictFromRoom(@RequestParam("idGuest") Long idGuest) {
        roomService.evictFromRoom(idGuest);
    }
}
