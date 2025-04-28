package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;
import com.kolayvergi.entity.vergi.KdvVergisi;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface KdvVergisiMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "alisveris", ignore = true)
//    KdvVergisi kdvVergisiCreateRequestToKdvVergisi(KdvVergisiCreateRequest request);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateKdvVergisiFromKdvVergisiUpdateRequest(KdvVergisiUpdateRequest request, @MappingTarget KdvVergisi entity);

    KdvVergisiResponse kdvVergisiToKdvVergisiResponse(KdvVergisi kdvVergisi);
}
