package com.kolayvergi.repository;

import com.kolayvergi.entity.Borc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorcRepository extends JpaRepository<Borc, Long> {
    Optional<Borc> getBorcByKullaniciId(Long kullaniciId);
}
