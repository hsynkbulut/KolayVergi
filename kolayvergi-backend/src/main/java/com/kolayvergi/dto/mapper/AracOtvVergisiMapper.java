package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.otv.AracOtvVergisiCreateRequest;
import com.kolayvergi.dto.request.otv.AracOtvVergisiUpdateRequest;
import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;
import com.kolayvergi.entity.vergi.AracOtvVergisi;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AracOtvVergisiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alisveris", ignore = true)
    AracOtvVergisi aracOtvVergisiCreateRequestToAracOtvVergisi(AracOtvVergisiCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAracOtvVergisiFromAracOtvVergisiUpdateRequest(AracOtvVergisiUpdateRequest request, @MappingTarget AracOtvVergisi entity);

    AracOtvVergisiResponse AracOtvVergisiToAracOtvVergisiResponse(AracOtvVergisi aracOtvVergisi);
}
