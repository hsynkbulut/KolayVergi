package com.kolayvergi.repository;

import com.kolayvergi.entity.Alisveris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlisverisRepository extends JpaRepository<Alisveris, UUID> {


}
