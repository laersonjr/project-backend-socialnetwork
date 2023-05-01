package com.sysmap.laersonjr.socialnetwork.domain.service;

import java.util.List;

public interface IModelMapperDTOConverter<T extends ModelMapperDTOConverter>{

    public <S, T> T convertToModelDTO(S entity, Class<T> dtoClass);
    public <S, T> List<T> convertToModelListDTO(List<S> entities, Class<T> dtoClass);
    public <S, T> T convertToEntity(S dto, Class<T> entity);
    public <S, T> void configureModelMapperForUpdate(S source, T target);

}
