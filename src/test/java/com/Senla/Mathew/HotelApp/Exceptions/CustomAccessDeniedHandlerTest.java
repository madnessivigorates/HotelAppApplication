package com.Senla.Mathew.HotelApp.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class CustomAccessDeniedHandlerTest {

    @Test
    public void testHandle() throws IOException {
        // Arrange
        CustomAccessDeniedHandler handler = new CustomAccessDeniedHandler();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        AccessDeniedException accessDeniedException = new AccessDeniedException("Доступ запрещен");

        // Мокируем PrintWriter
        PrintWriter writer = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        // Act
        handler.handle(request, response, accessDeniedException);

        // Assert
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
        verify(writer).print("{\"error\": \"У вас недостаточно доступа\"}");
        verify(writer).flush();
    }
}
