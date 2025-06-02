package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.response.vergi.MtvVergisiResponse;
import com.kolayvergi.entity.vergi.MtvVergisi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MtvVergisiMapper {

    MtvVergisiResponse mtvVergisiToMtvVergisiResponse(MtvVergisi mtvVergisi);
}
