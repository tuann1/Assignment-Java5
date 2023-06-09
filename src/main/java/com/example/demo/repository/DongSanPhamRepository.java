package com.example.demo.repository;

import com.example.demo.entity.DongSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DongSanPhamRepository extends JpaRepository<DongSanPham, UUID> {

    @Query(value = "SELECT * FROM DongSP WHERE Ma LIKE %?1% ", nativeQuery = true)
    Page<DongSanPham> search(String ma, Pageable pageNumber);

}
