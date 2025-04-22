package com.kolayvergi.repository;

import com.kolayvergi.entity.VergiKasasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VergiKasasiRepository extends JpaRepository<VergiKasasi, Long> {

}
