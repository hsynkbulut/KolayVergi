package com.kolayvergi.service;

import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;

import java.util.UUID;

public interface AlisverisService {

    AlisverisResponse createAlisveris(AlisverisCreateRequest request);
    AlisverisResponse getAlisveris(UUID id);
    AlisverisResponse updateAlisveris(UUID id, AlisverisCreateRequest updateAlisveris);
    void deleteAlisveris(UUID id);

}
