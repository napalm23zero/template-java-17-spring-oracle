package com.hustletech.template.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hustletech.template.application.dto.UserRequestDTO;
import com.hustletech.template.application.dto.UserResponseDTO;
import com.hustletech.template.domain.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends _GenericMapper<User, UserRequestDTO, UserResponseDTO> {

}
