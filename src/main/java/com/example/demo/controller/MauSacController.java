package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.service.MauSacService;
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
public class MauSacController {

    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }


    @GetMapping("/mau-sac/hien-thi")
    public String hienThi(Model model) {
        return listByPage(model, 1);
    }

    @GetMapping("/mau-sac/hien-thi/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {

        Page<MauSac> page = mauSacService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<MauSac> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("ms", new MauSac());
        model.addAttribute("listMauSac", list);
        return "mau-sac";
    }

    @GetMapping("/mau-sac/detail/{id}")
    public String detailMauSac(@PathVariable("id") UUID id, Model model,
                               @PathVariable("pageNumber") int currentPage) {
        Optional<MauSac> mauSac = mauSacService.getMauSac(id);
        model.addAttribute("ms", mauSac);

        Page<MauSac> page = mauSacService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<MauSac> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listMauSac", list);
        return "mau-sac";
    }

    @GetMapping("/mau-sac/remove/{id}")
    public String removeMauSac(@PathVariable("id") UUID id, Model model) {
        mauSacService.removeMauSac(id);
        return listByPage(model, 1);
    }

    @GetMapping("/mau-sac/add")
    public String addMauSac(Model model) {
        model.addAttribute("ms", new MauSac());
        return "mau-sac";
    }

    @PostMapping("/mau-sac/add")
    public String addMauSac1(Model model, @Valid @ModelAttribute("ms") MauSac mauSac,
                             BindingResult result, @RequestParam("ma") String ma) {
        if (result.hasErrors()) {
            return "mau-sac";
        } else {
            MauSac findMauSac = mauSacService.findMauSacByMa(ma);
            if (findMauSac == null) {
                mauSacService.addMauSac(mauSac);
                return listByPage(model, 1);
            } else {
                model.addAttribute("msg", "Mã trùng, Vui lòng kiểm tra lại!");
                return listByPage(model, 1);
            }
        }
    }

    @GetMapping("/mau-sac/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        Optional<MauSac> ms = mauSacService.getMauSac(id);
        model.addAttribute("ms", ms);
        return "update-mau-sac";
    }

    @PostMapping("/mau-sac/update")
    public String updateMauSac(Model model, @Valid @ModelAttribute("ms") MauSac mauSac,
                               BindingResult result, @RequestParam("id") UUID id) {
        if (result.hasErrors()) {
            return "update-mau-sac";
        }
        mauSacService.updateMauSac(mauSac, id);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/mau-sac/search/{pageNumber}")
    public String searchMauSac(Model model, @RequestParam("ma") String ma,
                               @PathVariable("pageNumber") int currentPage) {

        Page<MauSac> page = mauSacService.search(ma, currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<MauSac> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listMauSac", list);
        model.addAttribute("ms", new MauSac());
        return "mau-sac";
    }

}
