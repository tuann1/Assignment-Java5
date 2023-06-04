package com.example.demo.service.impl;

import com.example.demo.entity.MauSac;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MauSacServiceImplement implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public Page<MauSac> getAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return mauSacRepository.findAll(pageable);
    }

    @Override
    public Optional<MauSac> getMauSac(UUID id) {
        return mauSacRepository.findById(id);
    }

    @Override
    public MauSac findMauSacByMa(String ma) {
        List<MauSac> ds = mauSacRepository.findAll();
        for (MauSac mauSac : ds) {
            if (mauSac.getMa().equals(ma)) {
                return mauSac;
            }
        }
        return null;
    }

    @Override
    public void addMauSac(MauSac mauSac) {
        mauSacRepository.save(mauSac);
    }

    @Override
    public void updateMauSac(MauSac mauSac, UUID id) {
        MauSac mauSac1 = mauSacRepository.findById(id).get();
        mauSac1.setMa(mauSac.getMa());
        mauSac1.setTen(mauSac.getTen());
        mauSacRepository.save(mauSac1);
    }

    @Override
    public void removeMauSac(UUID id) {
        mauSacRepository.deleteById(id);
    }

    @Override
    public Page<MauSac> search(String ma, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return mauSacRepository.search(ma, pageable);
    }

}
