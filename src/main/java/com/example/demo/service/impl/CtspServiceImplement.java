package com.example.demo.service.impl;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.repository.CtspRepository;
import com.example.demo.service.CtspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CtspServiceImplement implements CtspService {

    @Autowired
    CtspRepository ctspRepository;

    @Override
    public Page<ChiTietSanPham> getAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return ctspRepository.findAll(pageable);
    }

    @Override
    public Optional<ChiTietSanPham> getChiTietSanPham(UUID id) {
        return ctspRepository.findById(id);
    }

    @Override
    public void addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        ctspRepository.save(chiTietSanPham);
    }

    @Override
    public void updateChiTietSanPham(ChiTietSanPham chiTietSanPham, UUID id) {
        ChiTietSanPham ctsp = ctspRepository.findById(id).get();
        ctsp.setSanPham(chiTietSanPham.getSanPham());
        ctsp.setNsx(chiTietSanPham.getNsx());
        ctsp.setMauSac(chiTietSanPham.getMauSac());
        ctsp.setDongSanPham(chiTietSanPham.getDongSanPham());
        ctsp.setNamBaoHanh(chiTietSanPham.getNamBaoHanh());
        ctsp.setMoTa(chiTietSanPham.getMoTa());
        ctsp.setSoLuongTon(chiTietSanPham.getSoLuongTon());
        ctsp.setGiaNhap(chiTietSanPham.getGiaNhap());
        ctsp.setGiaBan(chiTietSanPham.getGiaBan());
        ctspRepository.save(ctsp);
    }

    @Override
    public void removeChiTietSanPham(UUID id) {
        ctspRepository.deleteById(id);
    }

}
