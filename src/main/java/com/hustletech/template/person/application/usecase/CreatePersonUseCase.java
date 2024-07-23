package com.hustletech.template.person.application.usecase;

import org.springframework.stereotype.Component;

import com.hustletech.template.person.adapter.service.PersonService;
import com.hustletech.template.person.application.dto.PersonRequestDTO;
import com.hustletech.template.person.application.dto.PersonResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePersonUseCase {

    private final PersonService personService;

    public PersonResponseDTO execute(PersonRequestDTO requestDTO) {
        return personService.create(requestDTO);
    }
}