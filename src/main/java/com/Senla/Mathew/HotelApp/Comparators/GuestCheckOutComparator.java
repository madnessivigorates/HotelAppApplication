package com.Senla.Mathew.HotelApp.Comparators;

import com.Senla.Mathew.HotelApp.DTO.Guest.GuestDto;

import java.util.Comparator;

public class GuestCheckOutComparator implements Comparator<GuestDto> {
    public int compare(GuestDto gst1, GuestDto gst2) {
        if (gst1.checkOutDate() == null && gst2.checkOutDate() == null) {
            return 0;
        }
        if (gst1.checkOutDate() == null) {
            return 1;
        }
        if (gst2.checkOutDate() == null) {
            return -1;
        }
        return gst1.checkOutDate().compareTo(gst2.checkOutDate());
    }
}
