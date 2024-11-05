package com.Senla.Mathew.HotelApp.ModelsControllers;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.Models.Guest;
import com.Senla.Mathew.HotelApp.Models.Service;
import com.Senla.Mathew.HotelApp.ModelsServices.GuestServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public void createGuest(@RequestBody Guest guest) {
        guestService.createGuest(guest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guest/add-service")
    public void addService(@RequestBody Service service, @RequestParam("idGuest") Long idGuest) {
        guestService.addService(service, idGuest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<GuestDto>  showGuests(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return guestService.showGuests(sortBy);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/uninhabited")
    public List<GuestDto>  showUninhabitedGuests(@RequestParam(defaultValue = "withoutSorting") String sortBy) {
        return guestService.showUninhabitedGuests(sortBy);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/guest")
    public GuestDto getGuest(@RequestParam("guestId") Long guestId) {
        return guestService.getGuest(guestId);
    }
}
