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

import com.hustletech.template.domain.entity.User;
import com.hustletech.template.domain.repository.UserRepository;
import com.hustletech.template.shared.exception.NotFoundException;
import com.hustletech.template.user.adapter.service.UserService;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;
import com.hustletech.template.user.application.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

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

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setName("Peter Parker");
        userResponseDTO.setEmail("peter.parker@dailybugle.com");
        userResponseDTO.setBirthday(LocalDate.of(2001, 8, 10));
    }

    @Test
    void testGetUser_Success() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.entityToResponseDto(user)).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO result = userService.get(1L);

        // Assert
        assertEquals(userResponseDTO, result);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUser_NotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.get(1L));
        assertEquals("User with ID 1 not found", exception.getMessage());
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        when(userMapper.requestDtoToEntity(userRequestDTO)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.entityToResponseDto(user)).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO result = userService.create(userRequestDTO);

        // Assert
        assertEquals(userResponseDTO, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_Success() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.entityToResponseDto(user)).thenReturn(userResponseDTO);

        // Act
        UserResponseDTO result = userService.update(1L, userRequestDTO);

        // Assert
        assertEquals(userResponseDTO, result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> userService.update(1L, userRequestDTO));
        assertEquals("User with ID 1 not found", exception.getMessage());
    }

    @Test
    void testDeleteUser_Success() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        userService.delete(1L);

        // Assert
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUser_NotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.delete(1L));
        assertEquals("User with ID 1 not found", exception.getMessage());
    }
}
