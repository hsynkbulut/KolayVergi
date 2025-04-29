package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.MtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.MtvVergisi;

import java.util.List;

public interface MtvVergisiService {
    MtvVergisi createMtvVergisi(Alisveris alisveris, Kullanici kullanici);
    List<MtvVergisiResponse> getAllByAlisverisId(Long alisverisId);
    MtvVergisiResponse getMtvVergisiById(Long id);
}
