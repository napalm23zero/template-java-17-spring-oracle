package com.hustletech.template.person.application.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hustletech.template.person.adapter.service.PersonService;
import com.hustletech.template.person.application.dto.PersonFilterDTO;
import com.hustletech.template.person.application.dto.PersonResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPersonUseCase {

    private final PersonService personService;

    public Page<PersonResponseDTO> execute(PersonFilterDTO filter, Pageable pageable) {
        return personService.findAll(filter, pageable);
    }
}
