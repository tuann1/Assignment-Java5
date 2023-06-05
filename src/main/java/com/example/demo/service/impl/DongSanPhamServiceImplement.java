package com.example.demo.service.impl;

import com.example.demo.entity.DongSanPham;
import com.example.demo.repository.DongSanPhamRepository;
import com.example.demo.service.DongSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DongSanPhamServiceImplement implements DongSanPhamService {

    @Autowired
    DongSanPhamRepository dongSanPhamRepository;

    @Override
    public Page<DongSanPham> getAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return dongSanPhamRepository.findAll(pageable);
    }

    @Override
    public Optional<DongSanPham> getDongSanPham(UUID id) {
        return dongSanPhamRepository.findById(id);
    }

    @Override
    public DongSanPham findDongSanPhamByMa(String ma) {
        List<DongSanPham> dongSp = dongSanPhamRepository.findAll();
        for (DongSanPham dongSanPham : dongSp) {
            if (dongSanPham.getMa().equals(ma)) {
                return dongSanPham;
            }
        }
        return null;
    }

    @Override
    public void addDongSanPham(DongSanPham dongSanPham) {
        dongSanPhamRepository.save(dongSanPham);
    }

    @Override
    public void updateDongSanPham(DongSanPham dongSanPham, UUID id) {
        DongSanPham dongSp = dongSanPhamRepository.findById(id).get();
        dongSp.setMa(dongSanPham.getMa());
        dongSp.setTen(dongSanPham.getTen());
        dongSanPhamRepository.save(dongSp);
    }

    @Override
    public void removeDongSanPham(UUID id) {
        dongSanPhamRepository.deleteById(id);
    }

    @Override
    public Page<DongSanPham> search(String ma, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return dongSanPhamRepository.search(ma, pageable);
    }


}
