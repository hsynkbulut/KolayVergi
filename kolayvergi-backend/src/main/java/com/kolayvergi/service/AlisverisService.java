package com.kolayvergi.service;

import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;

import java.util.UUID;
import java.util.List;

public interface AlisverisService {

    AlisverisResponse createAlisveris(AlisverisCreateRequest request);
    AlisverisResponse getAlisveris(UUID id);
    AlisverisResponse updateAlisveris(UUID id, AlisverisUpdateRequest updateAlisveris);
    void deleteAlisveris(UUID id);
    List<AlisverisResponse> getCurrentUserAlisverisler();

}
