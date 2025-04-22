package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.VergiKasasiCreateRequest;
import com.kolayvergi.dto.response.VergiKasasiResponse;
import com.kolayvergi.entity.VergiKasasi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VergiKasasiMapper {
    @Mapping(target = "id", ignore = true)
    VergiKasasi vergiKasasiCreateRequestToVergiKasasi(VergiKasasiCreateRequest request);

    VergiKasasi vergiKasasiResponseToBorc(VergiKasasiResponse vergiKasasiResponse);
    VergiKasasiResponse vergiKasasiToVergiKasasiResponse(VergiKasasi vergiKasasi);
}