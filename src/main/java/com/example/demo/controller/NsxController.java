package com.example.demo.controller;

import com.example.demo.entity.NSX;
import com.example.demo.service.NsxService;
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
public class NsxController {

    @Autowired
    NsxService nsxService;

    @GetMapping("/nsx/hien-thi")
    public String hienThi(Model model) {
        return listByPage(model, 1);
    }

    @GetMapping("/nsx/hien-thi/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {

        Page<NSX> page = nsxService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<NSX> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("nsx", new NSX());
        model.addAttribute("listNsx", list);
        return "nsx";
    }

    @GetMapping("/nsx/detail/{id}")
    public String detailMauSac(@PathVariable("id") UUID id, Model model,
                               @PathVariable("pageNumber") int currentPage) {
        Optional<NSX> nsx = nsxService.getNsx(id);
        model.addAttribute("nsx", nsx);

        Page<NSX> page = nsxService.getAll(currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<NSX> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listNsx", list);
        return "nsx";
    }

    @GetMapping("/nsx/remove/{id}")
    public String removeMauSac(@PathVariable("id") UUID id, Model model) {
        nsxService.removeNsx(id);
        return listByPage(model, 1);
    }

    @GetMapping("/nsx/add")
    public String addMauSac(Model model) {
        model.addAttribute("nsx", new NSX());
        return "nsx";
    }

    @PostMapping("/nsx/add")
    public String addMauSac1(Model model, @Valid @ModelAttribute("nsx") NSX nsx,
                             BindingResult result, @RequestParam("ma") String ma) {
        if (result.hasErrors()) {
            return "nsx";
        } else {
            NSX findNsx = nsxService.findNsxByMa(ma);
            if (findNsx == null) {
                nsxService.addNsx(nsx);
                return listByPage(model, 1);
            } else {
                model.addAttribute("msg", "Mã trùng, Vui lòng kiểm tra lại!");
                return listByPage(model, 1);
            }
        }
    }

    @GetMapping("/nsx/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        Optional<NSX> nsx = nsxService.getNsx(id);
        model.addAttribute("nsx", nsx);
        return "update-nsx";
    }

    @PostMapping("/nsx/update")
    public String updateMauSac(Model model, @Valid @ModelAttribute("nsx") NSX nsx,
                               BindingResult result, @RequestParam("id") UUID id) {
        if (result.hasErrors()) {
            return "update-nsx";
        }
        nsxService.updateNsx(nsx, id);
        return "redirect:/nsx/hien-thi";
    }

    @GetMapping("/nsx/search/{pageNumber}")
    public String searchMauSac(Model model, @RequestParam("ma") String ma,
                               @PathVariable("pageNumber") int currentPage) {

        Page<NSX> page = nsxService.search(ma, currentPage);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<NSX> list = page.getContent();

        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("listNsx", list);
        model.addAttribute("nsx", new NSX());
        return "nsx";
    }

}
