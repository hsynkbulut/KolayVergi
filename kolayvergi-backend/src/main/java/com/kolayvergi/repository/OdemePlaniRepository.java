package com.kolayvergi.repository;

import com.kolayvergi.entity.OdemePlani;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface OdemePlaniRepository extends JpaRepository<OdemePlani, UUID> {
    Optional<OdemePlani> findByAlisverisId(UUID alisverisId);
}
