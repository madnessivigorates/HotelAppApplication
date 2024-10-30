package com.Senla.Mathew.HotelApp.Comparators;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;
import com.Senla.Mathew.HotelApp.Models.Guest;

import java.util.Comparator;

public class GuestAlphabetComparator implements Comparator<GuestDto> {
    public int compare(GuestDto gst1, GuestDto gst2) {
        return CharSequence.compare(gst1.name(), gst2.name());
    }
}
