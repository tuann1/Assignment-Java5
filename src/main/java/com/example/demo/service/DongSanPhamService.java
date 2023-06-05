package com.example.demo.service;

import com.example.demo.entity.DongSanPham;
import com.example.demo.entity.MauSac;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface DongSanPhamService {

    Page<DongSanPham> getAll(int pageNumber);

    Optional<DongSanPham> getDongSanPham(UUID id);

    DongSanPham findDongSanPhamByMa(String ma);

    void addDongSanPham(DongSanPham dongSanPham);

    void updateDongSanPham(DongSanPham dongSanPham, UUID id);

    void removeDongSanPham(UUID id);

    Page<DongSanPham> search(String ma, int pageNumber);

}
