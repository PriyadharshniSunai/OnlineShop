package com.product.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Price")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String currency;
    private Double amount;
}
