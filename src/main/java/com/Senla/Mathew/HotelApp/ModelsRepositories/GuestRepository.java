package com.Senla.Mathew.HotelApp.ModelsRepositories;

import com.Senla.Mathew.HotelApp.Models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {

    @Query("FROM Guest g WHERE g.room IS NULL")
    List<Guest> findUninhabitedGuests();

}
