package com.hustletech.template.adapters.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.hustletech.template.adapters.exception.UserNotFoundException;
import com.hustletech.template.domain.entity.User;
import com.hustletech.template.domain.repository.UserRepository;
import com.hustletech.template.user.adapter.service.UserService;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;
import com.hustletech.template.user.application.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Peter Parker");
        user.setEmail("peter.parker@dailybugle.com");
        user.setBirthday(LocalDate.of(2001, 8, 10));

        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Peter Parker");
        userRequestDTO.setEmail("peter.parker@dailybugle.com");
        userRequestDTO.setBirthday(LocalDate.of(2001, 8, 10));

        userResponseDTO = UserMapper.toResponseDTO(user);
    }

    @Test
    void testGetUser_Success() {
        // arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // act
        UserResponseDTO result = userService.get(1L);

        // assert
        assertEquals(userResponseDTO, result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUser_NotFound() {
        // arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // act & assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.get(1L));
        assertEquals("User not found with id: 1", exception.getMessage());
    }

    @Test
    void testCreateUser_Success() {
        // arrange
        when(userRepository.save(any(User.class))).thenReturn(user);

        // act
        UserResponseDTO result = userService.create(userRequestDTO);

        // assert
        assertEquals(userResponseDTO, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_Success() {
        // arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // act
        UserResponseDTO result = userService.update(1L, userRequestDTO);

        // assert
        assertEquals(userResponseDTO, result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        // arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // act & assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.update(1L, userRequestDTO));
        assertEquals("User not found with id: 1", exception.getMessage());
    }

    @Test
    void testDeleteUser_Success() {
        // arrange
        when(userRepository.existsById(1L)).thenReturn(true);

        // act
        userService.delete(1L);

        // assert
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUser_NotFound() {
        // arrange
        when(userRepository.existsById(1L)).thenReturn(false);

        // act & assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.delete(1L));
        assertEquals("User not found with id: 1", exception.getMessage());
    }
}
