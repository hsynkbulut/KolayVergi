package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.mtv.MtvVergisiCreateRequest;
import com.kolayvergi.dto.request.mtv.MtvVergisiUpdateRequest;
import com.kolayvergi.dto.response.vergi.MtvVergisiResponse;
import com.kolayvergi.entity.vergi.MtvVergisi;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MtvVergisiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alisveris", ignore = true)
    MtvVergisi mtvVergisiCreateRequestToMtvVergisi(MtvVergisiCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMtvVergisiFromMtvVergisiUpdateRequest(MtvVergisiUpdateRequest request, @MappingTarget MtvVergisi entity);

    MtvVergisiResponse mtvVergisiToMtvVergisiResponse(MtvVergisi mtvVergisi);
}
