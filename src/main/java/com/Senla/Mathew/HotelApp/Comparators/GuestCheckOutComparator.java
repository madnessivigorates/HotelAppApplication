package com.Senla.Mathew.HotelApp.Comparators;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;

import java.util.Comparator;

public class GuestCheckOutComparator implements Comparator<GuestDto> {
    public int compare(GuestDto gst1, GuestDto gst2) {
        if (gst1.getCheckOutDate() == null && gst2.getCheckOutDate() == null) {
            return 0;
        }
        if (gst1.getCheckOutDate() == null) {
            return 1;
        }
        if (gst2.getCheckOutDate() == null) {
            return -1;
        }
        return gst1.getCheckOutDate().compareTo(gst2.getCheckOutDate());
    }
}
