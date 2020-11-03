package com.evstratov.market.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "vendor code cannot be empty")
    @Pattern(regexp = "\\d{8}", message = "vendor code has 8 digits")
    @Column(name = "vendor_code")
    private String vendorCode;

    @NotBlank(message = "title cannot be empty")
    @Size(min = 3, max = 50, message = "title nas to be from 3 to 50 symbols")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "description cannot be empty")
    @Size(min = 5, max = 1000, message = "short description has to be from 5 to 1000 symbols")
    @Column(name = "short_description")
    private String shortDescription;

    @NotBlank(message = "description cannot be empty")
    @Size(min = 10, max = 5000, message = "description has to be from 5 to 5000 symbols")
    @Column(name = "full_description")
    private String fullDescription;

    @Positive(message = "price has to be positive value")
    @Digits(integer = 12, fraction = 2, message = "price has to fit ddddd.dd format")
    @Column(name = "price")
    private double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "image")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
