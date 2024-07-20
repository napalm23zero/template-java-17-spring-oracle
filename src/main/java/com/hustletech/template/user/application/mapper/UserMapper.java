package com.hustletech.template.user.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hustletech.template.application.mapper._GenericMapper;
import com.hustletech.template.domain.entity.User;
import com.hustletech.template.user.application.dto.UserRequestDTO;
import com.hustletech.template.user.application.dto.UserResponseDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends _GenericMapper<User, UserRequestDTO, UserResponseDTO> {

}
