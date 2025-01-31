package com.hustletech.template.user.adapter.service;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hustletech.template.domain.entity.User;
import com.hustletech.template.domain.repository.UserRepository;
import com.hustletech.template.shared.adapters.service._GenericService;
import com.hustletech.template.shared.application.mapper._GenericMapper;
import com.hustletech.template.user.application.dto.UserFilterDTO;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;

@Service
@Transactional
public class UserService extends _GenericService<User, UserRequestDTO, UserResponseDTO, Long, UserFilterDTO> {

    public UserService(UserRepository userRepository,
            JpaSpecificationExecutor<User> specificationExecutor,
            _GenericMapper<User, UserRequestDTO, UserResponseDTO> userMapper) {
        super(userRepository, specificationExecutor, userMapper, "User");
    }
}
