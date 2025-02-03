package com.product.OnlineShop.service;

import com.product.OnlineShop.entity.Product;
import com.product.OnlineShop.exception.ProductNotFoundException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductService {
   public Product saveProduct(Product product);

   public List<Product> fetchProductList(String category) throws ProductNotFoundException;

   public Product updateProduct(Product product,Long productID);

   //Using Feign client
   public String getMessage();

   public String getMessage1();

   public CompletableFuture<String> getFirstMessage() throws InterruptedException;

   public CompletableFuture<String> getSecondmessage();
}
