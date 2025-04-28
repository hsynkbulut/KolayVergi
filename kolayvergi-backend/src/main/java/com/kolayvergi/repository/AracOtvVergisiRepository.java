package com.kolayvergi.repository;

import com.kolayvergi.entity.vergi.AracOtvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AracOtvVergisiRepository extends JpaRepository<AracOtvVergisi, Long> {

    // Belirli bir alışverişe bağlı tüm KDV vergilerini çekecek.
    // ORNEK GET endpoint’i: /kdv/alisveris/{id}
    List<AracOtvVergisi> findByAlisverisId(Long alisverisId);
}
