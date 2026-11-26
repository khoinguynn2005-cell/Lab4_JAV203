package com.example.lab4.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // getter/setter: th:field đọc qua getter, Spring ghi dữ liệu form qua setter
@NoArgsConstructor   // constructor rỗng: Spring dùng để tạo bean khi gặp @ModelAttribute
@AllArgsConstructor
public class    ProductForm {
    @NotBlank(message = "{product.id.NotBlank}")
    private String id;

    @NotBlank(message = "{product.name.NotBlank}")
    private String name;

    @NotNull(message = "{product.price.NotNull}")
    @Positive(message = "{product.price.Positive}")
    @NumberFormat(pattern = "#.##")         // hiện 25000000 thay vì 2.5E7 trong ô nhập
    private Double price;

    @NotNull(message = "{product.quantity.NotNull}")
    @Min(value = 0, message = "{product.quantity.Min}")
    private Integer quantity;

    @NotNull(message = "{product.importDate.NotNull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // chuyển chuỗi "2026-11-19" từ form <-> Date
    private Date importDate;

    private Boolean available;

    @NotBlank(message = "{product.categoryId.NotBlank}")
    private String categoryId;

    private String image;

    @Size(max = 200, message = "{product.description.Size}")
    private String description;

    @Email(message = "{product.managerEmail.Email}") // để trống thì hợp lệ, có nhập thì phải đúng định dạng
    private String managerEmail;
}
