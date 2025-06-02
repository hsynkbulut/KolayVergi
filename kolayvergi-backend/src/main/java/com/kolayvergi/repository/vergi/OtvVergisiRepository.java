package com.kolayvergi.repository.vergi;

import com.kolayvergi.entity.vergi.OtvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OtvVergisiRepository extends JpaRepository<OtvVergisi, UUID> {

    List<OtvVergisi> findByAlisverisId(UUID alisverisId);
}
