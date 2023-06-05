package com.example.demo.repository;

import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SanPhamReposiotry extends JpaRepository<SanPham, UUID> {

    @Query(value = "SELECT * FROM SanPham WHERE Ma LIKE %?1% ", nativeQuery = true)
    Page<SanPham> search(String ma, Pageable pageNumber);

}
