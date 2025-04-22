package com.kolayvergi.repository;

import com.kolayvergi.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

 //    Optional<Kullanici> findByVkn(String vkn);
 //Optional<Kullanici> findByTckn(String tckn);
 //    Optional<Kullanici> findByEmail(String email);


}
