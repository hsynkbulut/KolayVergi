package com.kolayvergi.repository;

import com.kolayvergi.entity.Borc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BorcRepository extends JpaRepository<Borc, UUID> {
    Optional<Borc> getBorcByKullaniciId(UUID kullaniciId);
}
