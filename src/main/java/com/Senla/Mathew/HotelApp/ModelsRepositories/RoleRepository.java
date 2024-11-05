package com.Senla.Mathew.HotelApp.ModelsRepositories;

import com.Senla.Mathew.HotelApp.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String username);
}
