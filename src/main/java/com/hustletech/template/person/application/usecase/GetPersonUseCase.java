package com.hustletech.template.person.application.usecase;

import org.springframework.stereotype.Component;

import com.hustletech.template.person.adapter.service.PersonService;
import com.hustletech.template.person.application.dto.PersonResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetPersonUseCase {

    private final PersonService personService;

    public PersonResponseDTO execute(Long id) {
        return personService.get(id);
    }

}
