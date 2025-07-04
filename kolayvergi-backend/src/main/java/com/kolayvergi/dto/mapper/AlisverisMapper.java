package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.Alisveris;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {AracBilgisiMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlisverisMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kullanici", ignore = true)
    @Mapping(target = "aracBilgisi", ignore = true)
    Alisveris alisverisCreateRequestToAlisveris(AlisverisCreateRequest alisverisCreateRequest);

    AlisverisResponse alisverisToAlisverisResponse(Alisveris alisveris);

    void updateAlisverisFromRequest(AlisverisUpdateRequest request, @MappingTarget Alisveris alisveris);
}
