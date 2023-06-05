package com.example.demo.repository;

import com.example.demo.entity.NSX;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NsxRepository extends JpaRepository<NSX, UUID> {

    @Query(value = "SELECT * FROM NSX WHERE Ma LIKE %?1% ", nativeQuery = true)
    Page<NSX> search(String ma, Pageable pageNumber);

}
