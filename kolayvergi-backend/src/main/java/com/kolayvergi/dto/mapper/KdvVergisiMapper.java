package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;
import com.kolayvergi.entity.vergi.KdvVergisi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KdvVergisiMapper {

    KdvVergisiResponse kdvVergisiToKdvVergisiResponse(KdvVergisi kdvVergisi);
}
