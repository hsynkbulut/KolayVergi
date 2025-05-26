package com.kolayvergi.repository;

import com.kolayvergi.entity.Taksit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaksitRepository extends JpaRepository<Taksit, UUID> {

    @Query("SELECT t FROM Taksit t WHERE t.taksitNo = :taksitNo")
    Optional<Taksit> findByTaksitNo(String taksitNo);


}