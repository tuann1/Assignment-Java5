package com.example.demo.controller;

import com.example.demo.entity.DongSanPham;
import com.example.demo.service.DongSanPhamService;
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
public class DongSanPhamController {

    @Autowired
    DongSanPhamService dongSanPhamService;

    @GetMapping("/dong-san-pham/hien-thi")
    public String hienThi(Model model) {
        return listByPage(model, 1);
    }

    @GetMapping("/dong-san-pham/hien-thi/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {

        Page<DongSanPham> page = dongSanPhamService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<DongSanPham> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("dongSp", new DongSanPham());
        model.addAttribute("listDongSp", list);
        return "dong-san-pham";
    }

    @GetMapping("/dong-san-pham/detail/{id}")
    public String detailMauSac(@PathVariable("id") UUID id, Model model,
                               @PathVariable("pageNumber") int currentPage) {
        Optional<DongSanPham> mauSac = dongSanPhamService.getDongSanPham(id);
        model.addAttribute("ms", mauSac);

        Page<DongSanPham> page = dongSanPhamService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<DongSanPham> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listDongSp", list);
        return "dong-san-pham";
    }

    @GetMapping("/dong-san-pham/remove/{id}")
    public String removeMauSac(@PathVariable("id") UUID id, Model model) {
        dongSanPhamService.removeDongSanPham(id);
        return listByPage(model, 1);
    }

    @GetMapping("/dong-san-pham/add")
    public String addMauSac(Model model) {
        model.addAttribute("dongSp", new DongSanPham());
        return "dong-san-pham";
    }

    @PostMapping("/dong-san-pham/add")
    public String addMauSac1(Model model, @Valid @ModelAttribute("dongSp") DongSanPham dongSp,
                             BindingResult result, @RequestParam("ma") String ma) {
        if (result.hasErrors()) {
            return "dong-san-pham";
        } else {
            DongSanPham findDongSp = dongSanPhamService.findDongSanPhamByMa(ma);
            if (findDongSp == null) {
                dongSanPhamService.addDongSanPham(dongSp);
                return listByPage(model, 1);
            } else {
                model.addAttribute("msg", "Mã trùng, Vui lòng kiểm tra lại!");
                return listByPage(model, 1);
            }
        }
    }

    @GetMapping("/dong-san-pham/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        Optional<DongSanPham> dongSp = dongSanPhamService.getDongSanPham(id);
        model.addAttribute("dongSp", dongSp);
        return "update-dong-san-pham";
    }

    @PostMapping("/dong-san-pham/update")
    public String updateMauSac(Model model, @Valid @ModelAttribute("dongSp") DongSanPham dongSp,
                               BindingResult result, @RequestParam("id") UUID id) {
        if (result.hasErrors()) {
            return "update-dong-san-pham";
        }
        dongSanPhamService.updateDongSanPham(dongSp, id);
        return "redirect:/dong-san-pham/hien-thi";
    }

    @GetMapping("/dong-san-pham/search/{pageNumber}")
    public String searchMauSac(Model model, @RequestParam("ma") String ma,
                               @PathVariable("pageNumber") int currentPage) {

        Page<DongSanPham> page = dongSanPhamService.search(ma, currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<DongSanPham> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listDongSp", list);
        model.addAttribute("dongSp", new DongSanPham());
        return "dong-san-pham";
    }

}
