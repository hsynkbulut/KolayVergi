package com.kolayvergi.repository;

import com.kolayvergi.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, UUID> {

    boolean existsByVkn(String vkn);

    Optional<Kullanici> findByEmail(String email);

    boolean existsByEmail(String email);
}
