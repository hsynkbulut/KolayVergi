package com.kolayvergi.repository;

import com.kolayvergi.entity.Taksit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaksitRepository extends JpaRepository<Taksit, Long> {

    @Query("SELECT t FROM Taksit t WHERE t.taksitNo = :taksitNo")
    Optional<Taksit> findByTaksitNo(String taksitNo);


}