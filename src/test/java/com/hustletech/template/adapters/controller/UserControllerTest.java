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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        // arrange
        when(createUserUseCase.execute(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // act
        ResponseEntity<UserResponseDTO> response = userController.createUser(userRequestDTO);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
    }

    @Test
    void testUpdateUser_Success() {
        // arrange
        when(updateUserUseCase.execute(any(Long.class), any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // act
        ResponseEntity<UserResponseDTO> response = userController.updateUser(1L, userRequestDTO);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
    }

    @Test
    void testDeleteUser_Success() {
        // arrange

        // act
        ResponseEntity<Void> response = userController.deleteUser(1L);

        // assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetUser_Success() {
        // arrange
        when(getUserUseCase.execute(any(Long.class))).thenReturn(userResponseDTO);

        // act
        ResponseEntity<UserResponseDTO> response = userController.getUser(1L);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());
    }
}
