package com.product.OnlineShop.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ProductResponse {
    private String Name;
    private String Brand;
    private String Description;
    private Price price;
    private Inventory inventory;
    private List<Attribute> attribute;
    private String message;

}
