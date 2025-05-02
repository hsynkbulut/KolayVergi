package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.response.BorcResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = ApiConstants.BORCLAR)
public interface BorcController {

    @GetMapping(path = ApiConstants.ID)
    ResponseEntity<BorcResponse> getBorcById(@PathVariable(name = "id") Long id);
}