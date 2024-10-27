package com.Senla.Mathew.HotelApp.ModelsControllers;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.DTO.Service.ServiceDto;
import com.Senla.Mathew.HotelApp.ModelsServices.GuestServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestDataController {

    private final GuestServiceImpl guestService;

    @Autowired
    public GuestDataController(GuestServiceImpl guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/create")
    public void createGuest(@RequestBody GuestDto guestDto) {
        guestService.createGuest(guestDto);
    }

    @PostMapping("/guest/add-service")
    public void addService(@RequestBody ServiceDto serviceDto, @RequestParam("idGuest") Long idGuest) {
        guestService.addService(serviceDto, idGuest);
    }

    @GetMapping()
    public List<GuestDto>  showGuests(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return guestService.showGuests(sortBy);
    }

    @GetMapping("/uninhabited")
    public List<GuestDto>  showUninhabitedGuests(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return guestService.showUninhabitedGuests(sortBy);
    }

    @GetMapping("/guest")
    public GuestDto getGuest(@RequestParam("guestId") Long guestId) {
        return guestService.getGuest(guestId);
    }
}
