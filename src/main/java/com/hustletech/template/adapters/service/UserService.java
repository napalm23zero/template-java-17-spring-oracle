package com.hustletech.template.adapters.service;

import org.springframework.stereotype.Service;

import com.hustletech.template.adapters.exception.UserNotFoundException;
import com.hustletech.template.application.dto.UserRequestDTO;
import com.hustletech.template.application.dto.UserResponseDTO;
import com.hustletech.template.application.mapper.UserMapper;
import com.hustletech.template.domain.entity.User;
import com.hustletech.template.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO get(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        existingUser.setName(userRequestDTO.getName());
        existingUser.setEmail(userRequestDTO.getEmail());
        existingUser.setBirthday(userRequestDTO.getBirthday());

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toResponseDTO(updatedUser);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
