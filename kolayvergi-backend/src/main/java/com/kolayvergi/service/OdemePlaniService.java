package com.kolayvergi.service;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.math.BigDecimal;
import java.util.List;

public interface OdemePlaniService {


    OdemePlani createOdemePlaniForAlisveris(Alisveris alisveris, BigDecimal odenecekTutar);
    OdemePlani updateOdemePlaniAfterPayment(Taksit taksit);




}
