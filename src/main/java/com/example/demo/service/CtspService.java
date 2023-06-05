package com.example.demo.service;

import com.example.demo.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface CtspService {

    Page<ChiTietSanPham> getAll(int pageNumber);

    Optional<ChiTietSanPham> getChiTietSanPham(UUID id);

    void addChiTietSanPham(ChiTietSanPham chiTietSanPham);

    void updateChiTietSanPham(ChiTietSanPham chiTietSanPham, UUID id);

    void removeChiTietSanPham(UUID id);

}
