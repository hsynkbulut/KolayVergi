package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;
import com.kolayvergi.entity.vergi.OtvVergisi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AracOtvVergisiMapper {

    AracOtvVergisiResponse aracOtvVergisiToAracOtvVergisiResponse(OtvVergisi otvVergisi);
}
