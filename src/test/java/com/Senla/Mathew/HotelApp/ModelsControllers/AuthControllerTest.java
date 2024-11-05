package com.Senla.Mathew.HotelApp.ModelsControllers;

import com.Senla.Mathew.HotelApp.DTO.JWT.JwtRequest;
import com.Senla.Mathew.HotelApp.DTO.JWT.JwtResponse;
import com.Senla.Mathew.HotelApp.Exceptions.AppError;
import com.Senla.Mathew.HotelApp.ModelsServices.UserService;
import com.Senla.Mathew.HotelApp.utils.JwtTokenUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenUtils jwtTokenUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthController authController;

    public AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAuthToken_Success() {
        // Arrange
        JwtRequest jwtRequest = new JwtRequest("user", "100");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        // Act
        ResponseEntity<?> response = authController.createAuthToken(jwtRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}