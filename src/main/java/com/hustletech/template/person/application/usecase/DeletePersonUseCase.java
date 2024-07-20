package com.hustletech.template.person.application.usecase;

import org.springframework.stereotype.Component;

import com.hustletech.template.person.adapter.service.PersonService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeletePersonUseCase {

    private final PersonService personService;

    public void execute(Long id) {
        personService.delete(id);
    }
}