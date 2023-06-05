package com.example.demo.service;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface SanPhamService {

    Page<SanPham> getAll(int pageNumber);

    Optional<SanPham> getSanPham(UUID id);

    SanPham findSanPhamByMa(String ma);

    void addSanPham(SanPham sanPham);

    void updateSanPham(SanPham sanPham, UUID id);

    void removeSanPham(UUID id);

    Page<SanPham> search(String ma, int pageNumber);

}
