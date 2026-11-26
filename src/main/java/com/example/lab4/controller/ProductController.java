package com.example.lab4.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.lab4.model.ProductForm;

import jakarta.validation.Valid;

@Controller
public class ProductController {
    @GetMapping({"/product/form", "/product/save"})
    public String form(@ModelAttribute("product") ProductForm form) {
        form.setImportDate(new Date());   // ngày nhập mặc định: hôm nay
        form.setAvailable(true);          // mặc định: đang kinh doanh
        form.setImage("product.png");     // ảnh mặc định
        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(@Valid @ModelAttribute("product") ProductForm form, Errors errors,
                       @RequestParam("imageFile") MultipartFile file,
                       Model model) throws IOException {
        // xử lý dữ liệu form và file upload
        if (file.isEmpty()) {
            form.setImage("product.png");
        } else {
            String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
            File dir = new File("uploads").getAbsoluteFile();
            dir.mkdirs();
            file.transferTo(new File(dir, fileName));   // chép file người dùng gửi lên vào ổ đĩa
            form.setImage(fileName);                    // có chọn ảnh -> lấy tên ảnh
        }

        if (errors.hasErrors()) {
            // dữ liệu sai -> quay lại form, bean vẫn nằm trong Model nên dữ liệu đã nhập không mất
            model.addAttribute("message", "msg.invalid");   // đưa KEY, view tra chữ theo ngôn ngữ
        } else {
            model.addAttribute("message", "msg.valid");
            model.addAttribute("saved", true);          // chỉ hiển thị thông tin khi dữ liệu hợp lệ
        }
        return "product/form";
    }
}
