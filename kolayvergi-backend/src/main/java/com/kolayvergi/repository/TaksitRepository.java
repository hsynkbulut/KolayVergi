package com.kolayvergi.repository;

import com.kolayvergi.entity.Taksit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaksitRepository extends JpaRepository<Taksit, UUID> {

    Optional<Taksit> findByTaksitNo(String taksitNo);

    List<Taksit> findByOdemePlani_Alisveris_KullaniciId(UUID kullaniciId);
}