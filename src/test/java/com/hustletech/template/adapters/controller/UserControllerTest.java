package com.hustletech.template.adapters.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.hustletech.template.user.adapter.controller.UserController;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;
import com.hustletech.template.user.application.usecase.CreateUserUseCase;
import com.hustletech.template.user.application.usecase.DeleteUserUseCase;
import com.hustletech.template.user.application.usecase.FindUserUseCase;
import com.hustletech.template.user.application.usecase.GetUserUseCase;
import com.hustletech.template.user.application.usecase.UpdateUserUseCase;

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

    @Mock
    private FindUserUseCase findUserUseCase;

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
        UserResponseDTO response = userController.createEntity(userRequestDTO);

        // Assert
        assertEquals(userResponseDTO, response);
    }

    @Test
    void testUpdateUser_Success() {
        // Arrange
        when(updateUserUseCase.execute(any(Long.class), any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO response = userController.updateEntity(1L, userRequestDTO);

        // Assert
        assertEquals(userResponseDTO, response);
    }

    @Test
    void testDeleteUser_Success() {
        // Arrange
        doNothing().when(deleteUserUseCase).execute(any(Long.class));

        // Act & Assert
        assertDoesNotThrow(() -> userController.deleteEntity(1L));
    }

    @Test
    void testGetUser_Success() {
        // Arrange
        when(getUserUseCase.execute(any(Long.class))).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO response = userController.getEntity(1L);

        // Assert
        assertEquals(userResponseDTO, response);
    }
}
