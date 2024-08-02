package com.hustletech.template.person.adapter.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.domain.entity.Person;
import com.hustletech.template.person.adapter.service.PersonService;
import com.hustletech.template.person.application.dto.PersonFilterDTO;
import com.hustletech.template.person.application.dto.PersonRequestDTO;
import com.hustletech.template.person.application.dto.PersonResponseDTO;
import com.hustletech.template.shared.adapters.controller._GenericController;

@RestController
@RequestMapping("/persons")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class PersonController
        extends _GenericController<Person, PersonRequestDTO, PersonResponseDTO, PersonFilterDTO, Long> {

    public PersonController(PersonService personService) {
        super(personService, PersonFilterDTO.class);
    }
}
