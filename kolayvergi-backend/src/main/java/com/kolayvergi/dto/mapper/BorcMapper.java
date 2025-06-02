package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.entity.Borc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BorcMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kullanici", ignore = true)
    Borc borcCreateRequestToBorc(BorcCreateRequest request);

    BorcResponse borcToBorcResponse(Borc borc);
}