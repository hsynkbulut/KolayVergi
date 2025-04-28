package com.kolayvergi.service;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.OdemePlani;
import com.kolayvergi.entity.Taksit;
import com.kolayvergi.entity.enums.OdemeTuru;

import java.util.List;

public interface OdemePlaniService {


    OdemePlani createOdemePlaniForAlisveris(Alisveris alisveris, int taksitSayisi, List<OdemeTuru> odemeTurleri);
    OdemePlani updateOdemePlaniAfterPayment(Taksit taksit);




}
