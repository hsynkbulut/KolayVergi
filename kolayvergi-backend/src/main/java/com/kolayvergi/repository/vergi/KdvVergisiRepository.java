package com.kolayvergi.repository.vergi;

import com.kolayvergi.entity.vergi.KdvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KdvVergisiRepository extends JpaRepository<KdvVergisi, UUID> {

    List<KdvVergisi> findByAlisverisId(UUID alisverisId);
}
