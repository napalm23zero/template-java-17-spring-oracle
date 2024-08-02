package com.hustletech.template.person.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hustletech.template.domain.entity.Person;
import com.hustletech.template.person.application.dto.PersonRequestDTO;
import com.hustletech.template.person.application.dto.PersonResponseDTO;
import com.hustletech.template.shared.application.mapper._GenericMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper extends _GenericMapper<Person, PersonRequestDTO, PersonResponseDTO> {

}
