package com.example.demo.service;

import com.example.demo.entity.MauSac;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface MauSacService {

    Page<MauSac> getAll(int pageNumber);

    Optional<MauSac> getMauSac(UUID id);

    MauSac findMauSacByMa(String ma);

    void addMauSac(MauSac mauSac);

    void updateMauSac(MauSac mauSac, UUID id);

    void removeMauSac(UUID id);

    Page<MauSac> search(String ma, int pageNumber);

}
