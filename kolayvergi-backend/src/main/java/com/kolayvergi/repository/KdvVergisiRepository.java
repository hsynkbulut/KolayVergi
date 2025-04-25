package com.kolayvergi.repository;

import com.kolayvergi.entity.vergi.KdvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KdvVergisiRepository extends JpaRepository<KdvVergisi, Long> {
}
