package com.hustletech.template.shared.adapters.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hustletech.template.shared.adapters.service._GenericService;
import com.hustletech.template.shared.utils.ParseSortUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Generic REST controller for managing CRUD operations on entities.
 * This abstract class provides standardized endpoints for basic entity
 * management.
 * Role-based access control is enforced using Spring Security annotations.
 */
@Validated
@RequiredArgsConstructor
public abstract class _GenericController<Entity, RequestDTO, ResponseDTO, FilterDTO, ID> {

    private final _GenericService<Entity, RequestDTO, ResponseDTO, ID, FilterDTO> service;
    private final Class<FilterDTO> filterClass;

    /**
     * Creates a new entity based on the provided DTO.
     * Only users with ADMIN role can create new entities.
     *
     * @param requestDTO the request data transfer object containing entity details
     * @return the response data transfer object containing created entity details
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDTO createEntity(@Valid @RequestBody RequestDTO requestDTO) {
        return service.create(requestDTO);
    }

    /**
     * Updates an existing entity identified by ID with the data in the provided
     * DTO.
     * Requires USER or ADMIN role.
     *
     * @param id         the ID of the entity to update
     * @param requestDTO the request data transfer object containing updated entity
     *                   details
     * @return the response data transfer object containing updated entity details
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseDTO updateEntity(@PathVariable ID id, @Valid @RequestBody RequestDTO requestDTO) {
        return service.update(id, requestDTO);
    }

    /**
     * Deletes an entity identified by ID.
     * Requires USER or ADMIN role.
     *
     * @param id the ID of the entity to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void deleteEntity(@PathVariable ID id) {
        service.delete(id);
    }

    /**
     * Retrieves an entity by its ID.
     * Requires USER or ADMIN role.
     *
     * @param id the ID of the entity to retrieve
     * @return the response data transfer object containing entity details
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseDTO getEntity(@PathVariable ID id) {
        return service.get(id);
    }

    /**
     * Retrieves a paginated list of entities based on the specified filters and
     * pagination settings.
     * Requires USER or ADMIN role.
     *
     * @param page   the page number to retrieve (default is 0)
     * @param size   the number of records per page (default is 10)
     * @param sort   the sorting criteria (default is "id,asc")
     * @param filter the filter criteria for the search
     * @return a ResponseEntity containing the paginated list of entities
     */
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<ResponseDTO>> searchEntities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id,asc") String sort,
            @RequestParam(required = false) FilterDTO filter) {

        if (filter == null) {
            try {
                filter = filterClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create an instance of filter class", e);
            }
        }

        Sort sortObj = ParseSortUtil.parseSortParameter(sort);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        Page<ResponseDTO> resultPage = service.findAll(filter, pageable);

        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }
}
