package com.Senla.Mathew.HotelApp.ModelsRepositories;

import com.Senla.Mathew.HotelApp.Models.GuestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestHistoryRepository extends JpaRepository<GuestHistory, Integer> {

    @Query("FROM GuestHistory WHERE idRoom = :idRoom ORDER BY checkOutDate")
    List<GuestHistory> historyOfLivers(@Param("idRoom") Long idRoom);
}
