package com.kolayvergi.repository.vergi;

import com.kolayvergi.entity.vergi.MtvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MtvVergisiRepository extends JpaRepository<MtvVergisi, UUID> {

    // Belirli bir alışverişe bağlı tüm KDV vergilerini çekecek.
    // ORNEK GET endpoint’i: /kdv/alisveris/{id}
    List<MtvVergisi> findByAlisverisId(UUID alisverisId);
}
