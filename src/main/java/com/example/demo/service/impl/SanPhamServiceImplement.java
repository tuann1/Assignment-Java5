package com.example.demo.service.impl;

import com.example.demo.entity.SanPham;
import com.example.demo.repository.SanPhamReposiotry;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SanPhamServiceImplement implements SanPhamService {

    @Autowired
    SanPhamReposiotry sanPhamReposiotry;

    @Override
    public Page<SanPham> getAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return sanPhamReposiotry.findAll(pageable);
    }

    @Override
    public Optional<SanPham> getSanPham(UUID id) {
        return sanPhamReposiotry.findById(id);
    }

    @Override
    public SanPham findSanPhamByMa(String ma) {
        List<SanPham> ds = sanPhamReposiotry.findAll();
        for (SanPham sanPham : ds) {
            if (sanPham.getMa().equals(ma)) {
                return sanPham;
            }
        }
        return null;
    }

    @Override
    public void addSanPham(SanPham sanPham) {
        sanPhamReposiotry.save(sanPham);
    }

    @Override
    public void updateSanPham(SanPham sanPham, UUID id) {
        SanPham sp = sanPhamReposiotry.findById(id).get();
        sp.setMa(sanPham.getMa());
        sp.setTen(sanPham.getTen());
        sanPhamReposiotry.save(sp);
    }

    @Override
    public void removeSanPham(UUID id) {
        sanPhamReposiotry.deleteById(id);
    }

    @Override
    public Page<SanPham> search(String ma, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return sanPhamReposiotry.search(ma, pageable);
    }

}
