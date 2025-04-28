package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.KdvVergisi;

import java.util.List;

public interface KdvVergisiService {
    KdvVergisi createKdvVergisi(Alisveris alisveris, Kullanici kullanici);
    List<KdvVergisiResponse> getAllByAlisverisId(Long alisverisId);
    KdvVergisiResponse getKdvVergisiById(Long id);
}
