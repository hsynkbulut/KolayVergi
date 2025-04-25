package com.kolayvergi.repository;

import com.kolayvergi.entity.vergi.MtvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MtvVergisiRepository extends JpaRepository<MtvVergisi, Long> {
}
