package com.kolayvergi.service;

import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;

public interface AlisverisService {

    AlisverisResponse createAlisveris(AlisverisCreateRequest request);
    AlisverisResponse getAlisveris(Long id);
    AlisverisResponse updateAlisveris(Long id, AlisverisCreateRequest updateAlisveris);
    void deleteAlisveris(Long id);

}
