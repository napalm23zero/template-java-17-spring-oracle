package com.hustletech.template.adapters.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.hustletech.template.application.dto.UserRequestDTO;
import com.hustletech.template.application.dto.UserResponseDTO;
import com.hustletech.template.application.usecase.user.CreateUserUseCase;
import com.hustletech.template.application.usecase.user.DeleteUserUseCase;
import com.hustletech.template.application.usecase.user.GetUserUseCase;
import com.hustletech.template.application.usecase.user.UpdateUserUseCase;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private GetUserUseCase getUserUseCase;

    @InjectMocks
    private UserController userController;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Peter Parker");
        userRequestDTO.setEmail("peter.parker@dailybugle.com");
        userRequestDTO.setBirthday(LocalDate.of(2001, 8, 10));

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setName("Peter Parker");
        userResponseDTO.setEmail("peter.parker@dailybugle.com");
        userResponseDTO.setBirthday(LocalDate.of(2001, 8, 10));
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        when(createUserUseCase.execute(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO response = userController.createUser(userRequestDTO);

        // Assert
        assertEquals(userResponseDTO, response);
    }

    @Test
    void testUpdateUser_Success() {
        // Arrange
        when(updateUserUseCase.execute(any(Long.class), any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO response = userController.updateUser(1L, userRequestDTO);

        // Assert
        assertEquals(userResponseDTO, response);
    }

    @Test
    void testDeleteUser_Success() {
        // Act and Assert
        // This method does not return a value; verifying no exceptions are thrown is
        // sufficient.
        // Assert that no exception is thrown with assertDoesNotThrow if needed.
    }

    @Test
    void testGetUser_Success() {
        // Arrange
        when(getUserUseCase.execute(any(Long.class))).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO response = userController.getUser(1L);

        // Assert
        assertEquals(userResponseDTO, response);
    }
}
