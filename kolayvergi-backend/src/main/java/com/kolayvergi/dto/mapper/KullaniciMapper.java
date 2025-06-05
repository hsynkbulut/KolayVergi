package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KullaniciMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alisverisler", ignore = true)
    @Mapping(target = "vkn", ignore = true)
    Kullanici kullaniciCreateRequestToKullanici(KullaniciCreateRequest request);

    KullaniciResponse kullaniciToKullaniciResponse(Kullanici kullanici);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alisverisler", ignore = true)
    void updateKullaniciFromRequest(KullaniciUpdateRequest request, @MappingTarget Kullanici kullanici);
}
