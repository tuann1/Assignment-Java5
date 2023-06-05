package com.example.demo.controller;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.DongSanPham;
import com.example.demo.entity.MauSac;
import com.example.demo.entity.NSX;
import com.example.demo.entity.SanPham;
import com.example.demo.repository.DongSanPhamRepository;
import com.example.demo.repository.MauSacRepository;
import com.example.demo.repository.NsxRepository;
import com.example.demo.repository.SanPhamReposiotry;
import com.example.demo.service.CtspService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CtspController {

    @Autowired
    CtspService ctspService;

    @Autowired
    DongSanPhamRepository dongSanPhamRepository;

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    NsxRepository nsxRepository;

    @Autowired
    SanPhamReposiotry sanPhamReposiotry;

    @GetMapping("/ctsp/hien-thi")
    public String hienThi(Model model) {
        return listByPage(model, 1);
    }

    @GetMapping("/ctsp/hien-thi/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {

        Page<ChiTietSanPham> page = ctspService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<ChiTietSanPham> list = page.getContent();
        List<DongSanPham> listDongSp = dongSanPhamRepository.findAll();
        List<MauSac> listMauSac = mauSacRepository.findAll();
        List<NSX> listNsx = nsxRepository.findAll();
        List<SanPham> listSanPham = sanPhamReposiotry.findAll();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("ctsp", new ChiTietSanPham());
        model.addAttribute("listCtsp", list);
        model.addAttribute("listDongSp", listDongSp);
        model.addAttribute("listMauSac", listMauSac);
        model.addAttribute("listNsx", listNsx);
        model.addAttribute("listSanPham", listSanPham);
        return "ctsp";
    }

    @GetMapping("/ctsp/detail/{id}")
    public String detailChiTietSanPham(@PathVariable("id") UUID id, Model model,
                                       @PathVariable("pageNumber") int currentPage) {
        Optional<ChiTietSanPham> ctsp = ctspService.getChiTietSanPham(id);
        model.addAttribute("ctsp", ctsp);

        Page<ChiTietSanPham> page = ctspService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<ChiTietSanPham> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listCtsp", list);
        return "ctsp";
    }

    @GetMapping("/ctsp/remove/{id}")
    public String removeChiTietSanPham(@PathVariable("id") UUID id, Model model) {
        ctspService.removeChiTietSanPham(id);
        return "redirect:/ctsp/hien-thi";
    }

    @GetMapping("/ctsp/add")
    public String addChiTietSanPham(Model model) {
        model.addAttribute("ctsp", new ChiTietSanPham());
        return "ctsp";
    }

    @PostMapping("/ctsp/add")
    public String addChiTietSanPham1(Model model, @Valid @ModelAttribute("ctsp") ChiTietSanPham ctsp,
                                     BindingResult result, @RequestParam("giaNhap") String giaNhap,
                                     @RequestParam("giaBan") String giaBan) {
        if (result.hasErrors()) {
            List<DongSanPham> listDongSp = dongSanPhamRepository.findAll();
            List<MauSac> listMauSac = mauSacRepository.findAll();
            List<NSX> listNsx = nsxRepository.findAll();
            List<SanPham> listSanPham = sanPhamReposiotry.findAll();

            model.addAttribute("listDongSp", listDongSp);
            model.addAttribute("listMauSac", listMauSac);
            model.addAttribute("listNsx", listNsx);
            model.addAttribute("listSanPham", listSanPham);
            return "ctsp";
        } else {
            if (Long.valueOf(giaNhap) > Long.valueOf(giaBan)) {
                model.addAttribute("msg", "Giá nhập không được lớn hơn giá bán");
                return listByPage(model, 1);
            } else {
                ctspService.addChiTietSanPham(ctsp);
                return listByPage(model, 1);
            }
        }
    }

    @GetMapping("/ctsp/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        Optional<ChiTietSanPham> ctsp = ctspService.getChiTietSanPham(id);
        List<DongSanPham> listDongSp = dongSanPhamRepository.findAll();
        List<MauSac> listMauSac = mauSacRepository.findAll();
        List<NSX> listNsx = nsxRepository.findAll();
        List<SanPham> listSanPham = sanPhamReposiotry.findAll();

        model.addAttribute("ctsp", ctsp);
        model.addAttribute("listDongSp", listDongSp);
        model.addAttribute("listMauSac", listMauSac);
        model.addAttribute("listNsx", listNsx);
        model.addAttribute("listSanPham", listSanPham);
        return "update-ctsp";
    }

    @PostMapping("/ctsp/update")
    public String updateChiTietSanPham(Model model, @Valid @ModelAttribute("ctsp") ChiTietSanPham ctsp,
                                       BindingResult result, @RequestParam("id") UUID id,
                                       @RequestParam("giaNhap") String giaNhap, @RequestParam("giaBan") String giaBan) {
        if (result.hasErrors()) {
            List<DongSanPham> listDongSp = dongSanPhamRepository.findAll();
            List<MauSac> listMauSac = mauSacRepository.findAll();
            List<NSX> listNsx = nsxRepository.findAll();
            List<SanPham> listSanPham = sanPhamReposiotry.findAll();

            model.addAttribute("listDongSp", listDongSp);
            model.addAttribute("listMauSac", listMauSac);
            model.addAttribute("listNsx", listNsx);
            model.addAttribute("listSanPham", listSanPham);
            return "update-ctsp";
        } else {
            if (Long.valueOf(giaNhap) > Long.valueOf(giaBan)) {
                model.addAttribute("msg1", "Giá nhập không được lớn hơn giá bán");
                return viewUpdate(model, id);
            } else {
                ctspService.updateChiTietSanPham(ctsp, id);
                return "redirect:/ctsp/hien-thi";
            }
        }
    }

}
