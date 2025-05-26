package com.kolayvergi.repository;

import com.kolayvergi.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, UUID> {
    boolean existsByVkn(String vkn); // VKN çakışma kontrolü için
    Optional<Kullanici> findByEmail(String email);
    boolean existsByEmail(String email);


 //    Optional<Kullanici> findByVkn(String vkn);
 //Optional<Kullanici> findByTckn(String tckn);


}
