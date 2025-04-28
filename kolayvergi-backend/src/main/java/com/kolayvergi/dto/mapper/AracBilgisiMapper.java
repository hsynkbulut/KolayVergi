package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.AracBilgisiCreateRequest;
import com.kolayvergi.dto.request.AracBilgisiUpdateRequest;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.entity.AracBilgisi;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AracBilgisiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alisveris", ignore = true)
    AracBilgisi AracBilgisiCreateRequestToAracBilgisi(AracBilgisiCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAracBilgisiFromAracBilgisiUpdateRequest(AracBilgisiUpdateRequest request, @MappingTarget AracBilgisi entity);

    AracBilgisiResponse aracBilgisiToAracBilgisiResponse(AracBilgisi aracBilgisi);
    AracBilgisi aracBilgisiResponseToAracBilgisi(AracBilgisiResponse aracBilgisiResponse);
}
