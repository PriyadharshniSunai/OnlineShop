package com.product.onlineshop.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ProductResponse {
    private String name;
    private String brand;
    private String description;
    private Price price;
    private Inventory inventory;
    private List<Attribute> attribute;
    private String message;

}
