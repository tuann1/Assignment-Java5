package com.example.demo.service;

import com.example.demo.entity.NSX;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface NsxService {

    Page<NSX> getAll(int pageNumber);

    Optional<NSX> getNsx(UUID id);

    NSX findNsxByMa(String ma);

    void addNsx(NSX nsx);

    void updateNsx(NSX nsx, UUID id);

    void removeNsx(UUID id);

    Page<NSX> search(String ma, int pageNumber);

}
