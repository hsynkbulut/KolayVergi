package com.kolayvergi.dto.mapper;


import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.dto.response.AracBilgisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {AracBilgisiMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlisverisMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kullanici", ignore = true)
    @Mapping(target = "aracBilgisi", ignore = true)
    Alisveris aliverisCreateRequestToAlisveris(AlisverisCreateRequest alisverisCreateRequest);

    @Mapping(source = "aracBilgisi", target = "aracBilgisi", qualifiedByName = "mapAracBilgisi")
    @Mapping(source = "id", target = "id")
    AlisverisResponse alisverisToAlisverisResponse(Alisveris alisveris);

    @Named("mapAracBilgisi")
    default AracBilgisiResponse mapAracBilgisi(AracBilgisi aracBilgisi) {
        if (ObjectUtils.isEmpty(aracBilgisi)) {
            return null;
        }
        return new AracBilgisiResponse(
            aracBilgisi.getMarka(),
            aracBilgisi.getModel(),
            aracBilgisi.getIlkTescilYili(),
            aracBilgisi.getMotorSilindirHacmi(),
            aracBilgisi.getAracTipi(),
            aracBilgisi.getAracYasi(),
            aracBilgisi.getAracKapasitesi(),
            aracBilgisi.getAracAgirligi()
        );
    }

    void updateAlisverisFromRequest(AlisverisUpdateRequest request, @MappingTarget Alisveris alisveris);
}
