package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import com.kolayvergi.entity.Borc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorcMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kullanici", ignore = true)
    @Mapping(target = "kullaniciId", ignore = true)
    Borc borcCreateRequestToBorc(BorcCreateRequest request);

    Borc borcResponseToBorc(BorcResponse borcResponse);
    BorcResponse borcToBorcResponse(Borc borc);
}