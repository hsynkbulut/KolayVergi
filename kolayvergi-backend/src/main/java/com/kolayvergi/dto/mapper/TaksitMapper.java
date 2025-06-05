package com.kolayvergi.dto.mapper;

import com.kolayvergi.dto.response.TaksitResponse;
import com.kolayvergi.entity.Taksit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaksitMapper {
    @Mapping(source = "odemePlani.id", target = "odemePlaniId")
    TaksitResponse taksitToTaksitResponse(Taksit taksit);
    List<TaksitResponse> taksitListToTaksitResponseList(List<Taksit> taksitler);
} 