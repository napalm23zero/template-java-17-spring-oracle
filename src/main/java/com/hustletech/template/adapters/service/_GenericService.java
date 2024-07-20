package com.hustletech.template.adapters.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hustletech.template.adapters.exception.NotFoundException;
import com.hustletech.template.application.mapper._GenericMapper;
import com.hustletech.template.domain.specification._GenericSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class _GenericService<Entity, RequestDTO, ResponseDTO, ID, FilterDTO> {

    private final JpaRepository<Entity, ID> repository;
    private final JpaSpecificationExecutor<Entity> specificationExecutor;
    private final _GenericMapper<Entity, RequestDTO, ResponseDTO> mapper;
    private final String entityName;

    @Transactional(readOnly = true)
    public ResponseDTO get(ID id) {
        Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(entityName, id.toString()));
        return mapper.entityToResponseDto(entity);
    }

    @Transactional
    public ResponseDTO create(RequestDTO requestDTO) {
        Entity entity = mapper.requestDtoToEntity(requestDTO);
        entity = repository.save(entity);
        return mapper.entityToResponseDto(entity);
    }

    @Transactional
    public ResponseDTO update(ID id, RequestDTO requestDTO) {
        Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(entityName, id.toString()));
        mapper.updateEntityFromRequestDto(requestDTO, entity);
        entity = repository.save(entity);
        return mapper.entityToResponseDto(entity);
    }

    @Transactional
    public void delete(ID id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException(entityName, id.toString()));
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ResponseDTO> findAll(FilterDTO filter, Pageable pageable) {
        Specification<Entity> spec = _GenericSpecification.byFilter(filter);
        return specificationExecutor.findAll(spec, pageable).map(mapper::entityToResponseDto);
    }
}
