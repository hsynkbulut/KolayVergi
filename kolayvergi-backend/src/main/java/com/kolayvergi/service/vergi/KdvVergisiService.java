package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;

import java.util.List;
import java.util.UUID;

public interface KdvVergisiService {
    KdvVergisi createKdvVergisi(Alisveris alisveris, Kullanici kullanici, OtvVergisi otvVergisi);
    List<KdvVergisiResponse> getAllByAlisverisId(UUID alisverisId);
    KdvVergisiResponse getKdvVergisiById(UUID id);
    KdvVergisi updateKdvVergisi(Alisveris alisveris, Kullanici kullanici, OtvVergisi otvVergisi);
}
