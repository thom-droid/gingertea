package com.thom.gingertea.domain.user.dto;

public interface DtoRequest<T> {

    T toEntity();
}
