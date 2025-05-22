package com.kolayvergi.repository.vergi;

import com.kolayvergi.entity.vergi.KdvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KdvVergisiRepository extends JpaRepository<KdvVergisi, Long> {

    // Belirli bir alışverişe bağlı tüm KDV vergilerini çekecek.
    // ORNEK GET endpoint’i: /kdv/alisveris/{id}
    List<KdvVergisi> findByAlisverisId(Long alisverisId);
}
