package com.example.lab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Trang chủ quản trị
    @GetMapping("/admin/index")
    public String index() {
        return "admin/index";
    }

    // Trang giới thiệu hệ thống quản lý sản phẩm
    @GetMapping("/admin/about")
    public String about() {
        return "admin/about";
    }
}
