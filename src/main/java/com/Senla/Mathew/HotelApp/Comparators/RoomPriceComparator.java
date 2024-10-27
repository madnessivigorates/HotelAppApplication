package com.Senla.Mathew.HotelApp.Comparators;


import com.Senla.Mathew.HotelApp.DTO.Room.RoomDto;
import com.Senla.Mathew.HotelApp.Models.Room;

import java.util.Comparator;

public class RoomPriceComparator implements Comparator<RoomDto> {
    public int compare(RoomDto rm1, RoomDto rm2) {
        return Double.compare(rm1.getRoomCost(), rm2.getRoomCost());
    }
}
