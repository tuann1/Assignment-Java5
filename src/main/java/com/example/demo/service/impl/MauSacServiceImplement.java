package com.example.demo.service.impl;

import com.example.demo.entity.MauSac;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MauSacServiceImplement implements MauSacService {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public Optional<MauSac> getMauSac(UUID id) {
        return mauSacRepository.findById(id);
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

}
