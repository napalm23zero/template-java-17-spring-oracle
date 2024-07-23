package com.hustletech.template.adapters.controller;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hustletech.template.shared.utils.ParseSortUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
public abstract class _GenericController<Entity, RequestDTO, ResponseDTO, FilterDTO, ID> {

    private final Function<RequestDTO, ResponseDTO> createUseCase;
    private final BiFunction<ID, RequestDTO, ResponseDTO> updateUseCase;
    private final Consumer<ID> deleteUseCase;
    private final Function<ID, ResponseDTO> getUseCase;
    private final BiFunction<FilterDTO, Pageable, Page<ResponseDTO>> findUseCase;
    private final Class<FilterDTO> filterClass;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createEntity(@Valid @RequestBody RequestDTO requestDTO) {
        return createUseCase.apply(requestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO updateEntity(@PathVariable ID id, @Valid @RequestBody RequestDTO requestDTO) {
        return updateUseCase.apply(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntity(@PathVariable ID id) {
        deleteUseCase.accept(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDTO getEntity(@PathVariable ID id) {
        return getUseCase.apply(id);
    }

    @GetMapping("/list")
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

        Page<ResponseDTO> resultPage = findUseCase.apply(filter, pageable);

        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }
}
