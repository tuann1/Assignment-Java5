package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.entity.SanPham;
import com.example.demo.service.SanPhamService;
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
public class SanPhamController {

    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/san-pham/hien-thi")
    public String hienThi(Model model) {
        return listByPage(model, 1);
    }

    @GetMapping("/san-pham/hien-thi/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {

        Page<SanPham> page = sanPhamService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<SanPham> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("sp", new SanPham());
        model.addAttribute("listSanPham", list);
        return "san-pham";
    }

    @GetMapping("/san-pham/detail/{id}")
    public String detailSanPham(@PathVariable("id") UUID id, Model model,
                                @PathVariable("pageNumber") int currentPage) {
        Optional<SanPham> sanPham = sanPhamService.getSanPham(id);
        model.addAttribute("sp", sanPham);

        Page<SanPham> page = sanPhamService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<SanPham> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listSanPham", list);
        return "san-pham";
    }

    @GetMapping("/san-pham/remove/{id}")
    public String removeSanPham(@PathVariable("id") UUID id, Model model) {
        sanPhamService.removeSanPham(id);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/san-pham/add")
    public String addSanPham(Model model) {
        model.addAttribute("sp", new MauSac());
        return "san-pham";
    }

    @PostMapping("/san-pham/add")
    public String addSanPham1(Model model, @Valid @ModelAttribute("sp") SanPham sanPham,
                              BindingResult result, @RequestParam("ma") String ma) {
        if (result.hasErrors()) {
            return "san-pham";
        } else {
            SanPham findSanPham = sanPhamService.findSanPhamByMa(ma);
            if (findSanPham == null) {
                sanPhamService.addSanPham(sanPham);
                return listByPage(model, 1);
            } else {
                model.addAttribute("msg", "Mã trùng, Vui lòng kiểm tra lại!");
                return listByPage(model, 1);
            }
        }
    }

    @GetMapping("/san-pham/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        Optional<SanPham> sanPham = sanPhamService.getSanPham(id);
        model.addAttribute("sp", sanPham);
        return "update-san-pham";
    }

    @PostMapping("/san-pham/update")
    public String updateSanPham(Model model, @Valid @ModelAttribute("sp") SanPham sanPham,
                                BindingResult result, @RequestParam("id") UUID id) {
        if (result.hasErrors()) {
            return "update-san-pham";
        }
        sanPhamService.updateSanPham(sanPham, id);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/san-pham/search/{pageNumber}")
    public String searchSanPham(Model model, @RequestParam("ma") String ma,
                                @PathVariable("pageNumber") int currentPage) {

        Page<SanPham> page = sanPhamService.search(ma, currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<SanPham> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listSanPham", list);
        model.addAttribute("sp", new SanPham());
        return "san-pham";
    }

}
