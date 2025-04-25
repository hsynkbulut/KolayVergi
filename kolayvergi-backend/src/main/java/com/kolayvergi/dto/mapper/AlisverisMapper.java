package com.kolayvergi.dto.mapper;


import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.Alisveris;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlisverisMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kullanici", ignore = true)
    Alisveris aliverisCreateRequestToAlisveris(AlisverisCreateRequest alisverisCreateRequest);

    AlisverisResponse alisverisToAlisverisResponse(Alisveris alisveris);

}
