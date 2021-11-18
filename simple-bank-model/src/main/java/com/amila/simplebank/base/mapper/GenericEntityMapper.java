package com.amila.simplebank.base.mapper;

import java.util.List;

public interface GenericEntityMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);
}
