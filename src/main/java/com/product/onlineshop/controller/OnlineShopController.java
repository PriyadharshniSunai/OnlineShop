package com.product.onlineshop.controller;

import com.product.onlineshop.entity.Product;
import com.product.onlineshop.exception.ProductNotFoundException;
import com.product.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class OnlineShopController {


    private ProductService productService;

    @Autowired
    public OnlineShopController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping(path = "/products/{category}", produces="application/json")
    public ResponseEntity<List<Product>> getProducts(@PathVariable String category, @RequestParam Integer quantity, @RequestParam Double price)throws ProductNotFoundException {
        List<Product> productList= productService.fetchProductList(category);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        Product productResponse= productService.saveProduct(product);
        return new ResponseEntity<>("New Product Added",HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable Long productId){
        Product productResponse= productService.updateProduct(product,productId);
        return new ResponseEntity<>("Product updated",HttpStatus.OK);
    }

    @GetMapping("/FeignMessage")
    public  ResponseEntity<String> getMessage(){
        String message= productService.getMessage();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/Sync")
    public  ResponseEntity<String> getMessages(){
        String message= productService.getMessage();
        String message1= productService.getMessage1();
        return new ResponseEntity<>(message+ ","+message1,HttpStatus.OK);
    }

    @GetMapping("/async")
    public CompletableFuture<ResponseEntity<String>> asyncCallerMethod() throws InterruptedException {
        CompletableFuture<String> firstMessage= productService.getFirstMessage();
        CompletableFuture<String> secondMessage= productService.getSecondmessage();
        CompletableFuture<String> result = CompletableFuture.allOf(firstMessage, secondMessage)
                .thenApply(__ -> firstMessage.join() + "," + secondMessage.join());

        return result.thenApply(message -> new ResponseEntity<>(message,HttpStatus.OK));
    }
}
