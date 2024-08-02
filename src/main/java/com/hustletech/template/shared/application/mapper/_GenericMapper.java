package com.hustletech.template.shared.application.mapper;

import org.mapstruct.MappingTarget;

/**
 * Generic mapper interface for converting between entities and data transfer
 * objects (DTOs).
 * This interface defines standard methods to map entity and DTO objects
 * bi-directionally,
 * and to update entities from DTOs.
 *
 * @param <Entity>      the domain entity type
 * @param <RequestDTO>  the request DTO type
 * @param <ResponseDTO> the response DTO type
 */
public interface _GenericMapper<Entity, RequestDTO, ResponseDTO> {

    /**
     * Converts a request DTO to its corresponding entity.
     *
     * @param requestDto the request DTO to be converted
     * @return the entity corresponding to the DTO
     */
    Entity requestDtoToEntity(RequestDTO requestDto);

    /**
     * Converts an entity to its corresponding response DTO.
     *
     * @param entity the entity to be converted
     * @return the response DTO corresponding to the entity
     */
    ResponseDTO entityToResponseDto(Entity entity);

    /**
     * Updates an entity with data from a request DTO.
     *
     * @param requestDto the request DTO providing update data
     * @param entity     the target entity to be updated
     */
    void updateEntityFromRequestDto(RequestDTO requestDto, @MappingTarget Entity entity);

}
