package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
