package com.hustletech.template.application.mapper;

import org.mapstruct.MappingTarget;

public interface _GenericMapper<Entity, RequestDTO, ResponseDTO> {

    Entity requestDtoToEntity(RequestDTO requestDto);

    ResponseDTO entityToResponseDto(Entity entity);

    void updateEntityFromRequestDto(RequestDTO requestDto, @MappingTarget Entity entity);

}
