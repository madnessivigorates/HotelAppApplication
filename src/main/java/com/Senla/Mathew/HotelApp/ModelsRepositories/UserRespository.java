package com.Senla.Mathew.HotelApp.ModelsRepositories;

import com.Senla.Mathew.HotelApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
