package com.example.demo.repository;

import com.example.demo.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, UUID> {

    @Query(value = "SELECT * FROM MauSac WHERE Ma LIKE %?1% ", nativeQuery = true)
    Page<MauSac> search(String ma, Pageable pageNumber);

}
