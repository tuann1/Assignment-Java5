package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.service.MauSacService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<MauSac> list = mauSacService.getAll();
        model.addAttribute("ms", new MauSac());
        model.addAttribute("listMauSac", list);
        return "mau-sac";
    }

    @GetMapping("/mau-sac/detail/{id}")
    public String detailMauSac(@PathVariable("id") UUID id, Model model) {
        Optional<MauSac> mauSac = mauSacService.getMauSac(id);
        model.addAttribute("ms", mauSac);

        List<MauSac> list = mauSacService.getAll();
        model.addAttribute("listMauSac", list);
        return "mau-sac";
    }

    @GetMapping("/mau-sac/remove/{id}")
    public String removeMauSac(@PathVariable("id") UUID id, Model model) {
        mauSacService.removeMauSac(id);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/mau-sac/add")
    public String addMauSac(Model model) {
        model.addAttribute("ms", new MauSac());
        return "mau-sac";
    }

    @PostMapping("/mau-sac/add")
    public String addMauSac1(Model model, @Valid @ModelAttribute("ms") MauSac mauSac,
                             BindingResult result) {
        if (result.hasErrors()) {
            List<MauSac> list = mauSacService.getAll();
            model.addAttribute("listMauSac", list);
            return "mau-sac";
        }
        mauSacService.addMauSac(mauSac);
        return "redirect:/mau-sac/hien-thi";
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

}
