package com.kolayvergi.repository;

import com.kolayvergi.entity.Alisveris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlisverisRepository extends JpaRepository<Alisveris, UUID> {

    List<Alisveris> findAllByKullaniciId(UUID kullaniciId);

    @Query("SELECT a FROM Alisveris a LEFT JOIN FETCH a.aracBilgisi WHERE a.id = :id")
    Optional<Alisveris> findByIdWithAracBilgisi(@Param("id") UUID id);
}
