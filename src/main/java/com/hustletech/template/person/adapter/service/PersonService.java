package com.hustletech.template.person.adapter.service;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hustletech.template.domain.entity.Person;
import com.hustletech.template.domain.repository.PersonRepository;
import com.hustletech.template.person.application.dto.PersonFilterDTO;
import com.hustletech.template.person.application.dto.PersonRequestDTO;
import com.hustletech.template.person.application.dto.PersonResponseDTO;
import com.hustletech.template.shared.adapters.service._GenericService;
import com.hustletech.template.shared.application.mapper._GenericMapper;

@Service
@Transactional
public class PersonService extends _GenericService<Person, PersonRequestDTO, PersonResponseDTO, Long, PersonFilterDTO> {

    public PersonService(PersonRepository personRepository,
            JpaSpecificationExecutor<Person> specificationExecutor,
            _GenericMapper<Person, PersonRequestDTO, PersonResponseDTO> personMapper) {
        super(personRepository, specificationExecutor, personMapper, "Person");
    }
}
