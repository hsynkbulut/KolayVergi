package com.kolayvergi.repository;

import com.kolayvergi.entity.vergi.AracOtvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AracOtvVergisiRepository extends JpaRepository<AracOtvVergisi, Long> {
}
