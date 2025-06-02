package com.kolayvergi.repository.vergi;

import com.kolayvergi.entity.vergi.MtvVergisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MtvVergisiRepository extends JpaRepository<MtvVergisi, UUID> {

    List<MtvVergisi> findByAlisverisId(UUID alisverisId);
}
