package com.Senla.Mathew.HotelApp.ModelsRepositories;

import com.Senla.Mathew.HotelApp.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("FROM Room r WHERE r.status = 'FREE' OR r.capacity > SIZE(r.listOfGuests)")
    List<Room> findFreeRooms();

    @Query("SELECT r FROM Room r WHERE :lookingDate > r.willBeFree")
    List<Room> findRoomsByAvailableDate(@Param("lookingDate") LocalDate lookingDate);

}
