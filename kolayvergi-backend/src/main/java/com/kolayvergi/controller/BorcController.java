package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.BorcCreateRequest;
import com.kolayvergi.dto.request.BorcUpdateRequest;
import com.kolayvergi.dto.response.BorcResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = ApiConstants.BORCLAR)
public interface BorcController {

    @PostMapping
    ResponseEntity<BorcResponse> createBorc(@RequestBody @Valid BorcCreateRequest request);

    @GetMapping(path = ApiConstants.ID)
    ResponseEntity<BorcResponse> getBorcById(@PathVariable(name = "id") Long id);

    @PutMapping(path = ApiConstants.ID)
    ResponseEntity<BorcResponse> updateBorc(@PathVariable(name = "id") Long id,
                                            @RequestBody BorcUpdateRequest borcUpdateRequest);
}