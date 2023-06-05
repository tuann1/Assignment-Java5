package com.example.demo.service.impl;

import com.example.demo.entity.NSX;
import com.example.demo.repository.NsxRepository;
import com.example.demo.service.NsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NsxServiceImplement implements NsxService {

    @Autowired
    NsxRepository nsxRepository;

    @Override
    public Page<NSX> getAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return nsxRepository.findAll(pageable);
    }

    @Override
    public Optional<NSX> getNsx(UUID id) {
        return nsxRepository.findById(id);
    }

    @Override
    public NSX findNsxByMa(String ma) {
        List<NSX> ds = nsxRepository.findAll();
        for (NSX nsx : ds) {
            if (nsx.getMa().equals(ma)) {
                return nsx;
            }
        }
        return null;
    }

    @Override
    public void addNsx(NSX nsx) {
        nsxRepository.save(nsx);
    }

    @Override
    public void updateNsx(NSX nsx, UUID id) {
        NSX n = nsxRepository.findById(id).get();
        n.setMa(nsx.getMa());
        n.setTen(nsx.getTen());
        nsxRepository.save(n);
    }

    @Override
    public void removeNsx(UUID id) {
        nsxRepository.deleteById(id);
    }

    @Override
    public Page<NSX> search(String ma, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return nsxRepository.search(ma, pageable);
    }

}
