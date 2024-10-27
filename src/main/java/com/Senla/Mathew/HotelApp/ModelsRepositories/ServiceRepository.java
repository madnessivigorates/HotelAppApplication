package com.Senla.Mathew.HotelApp.ModelsRepositories;

import com.Senla.Mathew.HotelApp.Models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
