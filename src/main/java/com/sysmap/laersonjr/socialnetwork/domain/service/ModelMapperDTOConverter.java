package com.sysmap.laersonjr.socialnetwork.domain.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperDTOConverter {

    private ModelMapper modelMapper;

    public <S, T> T convertToModelDTO(S entity, Class<T> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <S, T> List<T> convertToModelListDTO(List<S> entities, Class<T> dtoClass) {
        return entities.stream().map(entity -> convertToModelDTO(entity, dtoClass)).collect(Collectors.toList());
    }

    public <S, T> T convertToEntity(S dto, Class<T> entity) {
        return modelMapper.map(dto, entity);
    }

    public <S, T> void configureModelMapperForUpdate(S source, T target) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(source, target);
    }

}
