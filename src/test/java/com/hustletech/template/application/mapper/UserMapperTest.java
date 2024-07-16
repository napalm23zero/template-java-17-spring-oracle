package com.hustletech.template.application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.hustletech.template.application.dto.UserRequestDTO;
import com.hustletech.template.application.dto.UserResponseDTO;
import com.hustletech.template.domain.entity.User;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserMapperTest {

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
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());

        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("Peter Parker");
        userRequestDTO.setEmail("peter.parker@dailybugle.com");
        userRequestDTO.setBirthday(LocalDate.of(2001, 8, 10));

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setName("Peter Parker");
        userResponseDTO.setEmail("peter.parker@dailybugle.com");
        userResponseDTO.setBirthday(LocalDate.of(2001, 8, 10));
        userResponseDTO.setCreateDate(user.getCreateDate());
        userResponseDTO.setUpdateDate(user.getUpdateDate());
    }

    @Test
    void testToEntity_Success() {
        // act
        User result = UserMapper.toEntity(userRequestDTO);

        // assert
        assertEquals(userRequestDTO.getName(), result.getName());
        assertEquals(userRequestDTO.getEmail(), result.getEmail());
        assertEquals(userRequestDTO.getBirthday(), result.getBirthday());
    }

    @Test
    void testToResponseDTO_Success() {
        // act
        UserResponseDTO result = UserMapper.toResponseDTO(user);

        // assert
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getBirthday(), result.getBirthday());
        assertEquals(user.getCreateDate(), result.getCreateDate());
        assertEquals(user.getUpdateDate(), result.getUpdateDate());
    }
}
