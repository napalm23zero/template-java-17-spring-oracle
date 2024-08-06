package com.hustletech.template.shared.adapters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hustletech.template.domain.specification._GenericSpecification;
import com.hustletech.template.shared.application.mapper._GenericMapper;
import com.hustletech.template.shared.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * Abstract generic service providing CRUD operations for an entity.
 * This service utilizes Spring Data JPA repositories and specifications for
 * database operations,
 * and a generic mapper for converting between entities and DTOs.
 */
@Service
@RequiredArgsConstructor
public abstract class _GenericService<Entity, RequestDTO, ResponseDTO, ID, FilterDTO> {

    private final JpaRepository<Entity, ID> repository;
    private final JpaSpecificationExecutor<Entity> specificationExecutor;
    private final _GenericMapper<Entity, RequestDTO, ResponseDTO> mapper;
    private final String entityName;

    /**
     * Retrieves an entity by its ID and converts it to a response DTO.
     *
     * @param id The ID of the entity to retrieve
     * @return The response DTO of the entity
     * @throws NotFoundException If the entity is not found
     */
    @Transactional(readOnly = true)
    public ResponseDTO get(ID id) {
        Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(entityName, id.toString()));
        return mapper.entityToResponseDto(entity);
    }

    /**
     * Creates a new entity from a request DTO, saves it, and returns the response
     * DTO.
     *
     * @param requestDTO The request DTO to create a new entity
     * @return The response DTO of the created entity
     */
    @Transactional
    public ResponseDTO create(RequestDTO requestDTO) {
        Entity entity = mapper.requestDtoToEntity(requestDTO);
        entity = repository.save(entity);
        return mapper.entityToResponseDto(entity);
    }

    /**
     * Updates an existing entity identified by ID with the data from a request DTO.
     *
     * @param id         The ID of the entity to update
     * @param requestDTO The request DTO with updated data
     * @return The response DTO of the updated entity
     * @throws NotFoundException If the entity is not found
     */
    @Transactional
    public ResponseDTO update(ID id, RequestDTO requestDTO) {
        Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(entityName, id.toString()));
        mapper.updateEntityFromRequestDto(requestDTO, entity);
        entity = repository.save(entity);
        return mapper.entityToResponseDto(entity);
    }

    /**
     * Deletes an entity identified by ID.
     *
     * @param id The ID of the entity to delete
     * @throws NotFoundException If the entity is not found
     */
    @Transactional
    public void delete(ID id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException(entityName, id.toString()));
        repository.deleteById(id);
    }

    /**
     * Finds all entities matching a given filter and pageable settings.
     *
     * @param filter   The filter DTO to apply
     * @param pageable The pageable settings
     * @return A page of response DTOs
     * @throws NotFoundException If no entities are found
     */
    @Transactional(readOnly = true)
    public Page<ResponseDTO> findAll(FilterDTO filter, Pageable pageable) {
        Specification<Entity> spec = _GenericSpecification.byFilter(filter);
        Page<ResponseDTO> resultPage = specificationExecutor.findAll(spec, pageable).map(mapper::entityToResponseDto);

        if (resultPage.isEmpty()) {
            throw new NotFoundException(String.format("No %s found with the specified criteria.", entityName));
        }

        return resultPage;
    }
}
