package com.Senla.Mathew.HotelApp.ModelsServices;

import com.Senla.Mathew.HotelApp.Models.User;
import com.Senla.Mathew.HotelApp.ModelsRepositories.RoleRepository;
import com.Senla.Mathew.HotelApp.ModelsRepositories.UserRespository;
import org.hibernate.mapping.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRespository userRespository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("user","100" );
        user.setUserId(1L);
        user.setRoles(new ArrayList<>());
    }

    @Test
    public void testFindByUsername_UserExists() {
        // Arrange
        when(userRespository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        // Act
        Optional<User> result = userService.findByUsername(user.getUsername());
        // Assert
        assertTrue(result.isPresent());
        assertEquals(user.getUsername(), result.get().getUsername());
        verify(userRespository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    public void testFindByUsername_UserDoesNotExist() {
        // Arrange
        String username = "uSeR";
        when(userRespository.findByUsername(username)).thenReturn(Optional.empty());
        // Act
        Optional<User> result = userService.findByUsername(username);
        // Assert
        assertFalse(result.isPresent());
        verify(userRespository, times(1)).findByUsername(username);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        // Arrange
        when(userRespository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        // Act
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        // Assert
        assertNotNull(userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
        verify(userRespository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserDoesNotExist() {
        // Arrange
        String username = "uSeR";
        when(userRespository.findByUsername(username)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
        verify(userRespository, times(1)).findByUsername(username);
    }
}
