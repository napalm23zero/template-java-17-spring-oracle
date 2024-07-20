package com.hustletech.template.person.adapter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hustletech.template.adapters.controller._GenericController;
import com.hustletech.template.domain.entity.Person;
import com.hustletech.template.person.application.dto.PersonFilterDTO;
import com.hustletech.template.person.application.dto.PersonRequestDTO;
import com.hustletech.template.person.application.dto.PersonResponseDTO;
import com.hustletech.template.person.application.usecase.CreatePersonUseCase;
import com.hustletech.template.person.application.usecase.DeletePersonUseCase;
import com.hustletech.template.person.application.usecase.FindPersonUseCase;
import com.hustletech.template.person.application.usecase.GetPersonUseCase;
import com.hustletech.template.person.application.usecase.UpdatePersonUseCase;

@RestController
@RequestMapping("/persons")
public class PersonController
        extends _GenericController<Person, PersonRequestDTO, PersonResponseDTO, PersonFilterDTO, Long> {

    public PersonController(CreatePersonUseCase createPersonUseCase,
            UpdatePersonUseCase updatePersonUseCase,
            DeletePersonUseCase deletePersonUseCase,
            GetPersonUseCase getPersonUseCase,
            FindPersonUseCase findPersonUseCase) {
        super(
                createPersonUseCase::execute,
                updatePersonUseCase::execute,
                deletePersonUseCase::execute,
                getPersonUseCase::execute,
                findPersonUseCase::execute,
                PersonFilterDTO.class);
    }
}
