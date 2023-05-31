package com.example.demo.service;

import com.example.demo.entity.MauSac;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MauSacService {

    List<MauSac> getAll();

    Optional<MauSac> getMauSac(UUID id);

    void addMauSac(MauSac mauSac);

    void updateMauSac(MauSac mauSac, UUID id);

    void removeMauSac(UUID id);

}
