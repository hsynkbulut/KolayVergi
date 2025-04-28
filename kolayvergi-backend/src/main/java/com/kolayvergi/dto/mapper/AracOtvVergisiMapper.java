package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;
import com.kolayvergi.entity.vergi.AracOtvVergisi;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AracOtvVergisiMapper {

    AracOtvVergisiResponse aracOtvVergisiToAracOtvVergisiResponse(AracOtvVergisi aracOtvVergisi);
}
