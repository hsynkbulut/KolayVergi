package com.kolayvergi.repository;

import com.kolayvergi.entity.AracBilgisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AracBilgisiRepository extends JpaRepository<AracBilgisi, UUID> {
}
