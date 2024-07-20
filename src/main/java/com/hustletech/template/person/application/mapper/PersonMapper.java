package com.hustletech.template.person.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.hustletech.template.application.mapper._GenericMapper;
import com.hustletech.template.domain.entity.Person;
import com.hustletech.template.person.application.dto.PersonRequestDTO;
import com.hustletech.template.person.application.dto.PersonResponseDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper extends _GenericMapper<Person, PersonRequestDTO, PersonResponseDTO> {

}
