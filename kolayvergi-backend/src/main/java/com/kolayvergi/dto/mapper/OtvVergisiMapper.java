package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.response.vergi.OtvVergisiResponse;
import com.kolayvergi.entity.vergi.OtvVergisi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OtvVergisiMapper {

    OtvVergisiResponse otvVergisiToOtvVergisiResponse(OtvVergisi otvVergisi);
}
