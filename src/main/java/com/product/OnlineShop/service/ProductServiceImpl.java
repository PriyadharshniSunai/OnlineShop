package com.product.OnlineShop.service;

import com.product.OnlineShop.client.DemoClient;
import com.product.OnlineShop.entity.Product;
import com.product.OnlineShop.exception.ProductNotFoundException;
import com.product.OnlineShop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    DemoClient democlient;

    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product,Long productID){
        Product productByID= productRepository.findById(productID).get();
        ModelMapper modelMapper = new ModelMapper();
        productByID = modelMapper.map(product,Product.class);
        return productRepository.save(productByID);
    }


    @Override
    public String getMessage() {
        return democlient.getMessage();
    }

    @Override
    public String getMessage1() {
        return democlient.getMessage1();
    }

    @Async
    @Override
    public CompletableFuture<String> getFirstMessage() throws InterruptedException {
        return CompletableFuture.completedFuture(democlient.getMessage());
    }

    @Async
    @Override
    public CompletableFuture<String> getSecondmessage() {
        return CompletableFuture.completedFuture(democlient.getMessage1());
    }

    @Override
    public List<Product> fetchProductList(String category) throws ProductNotFoundException {
        List<Product> productList= productRepository.findByCategory(category);
        if(!productList.isEmpty()){
            List<Product> finalList= productList.stream().filter(product -> product.getAvailable() > 9 || (product.getAvailable() - product.getReserved() > 9)).toList();
            if(!finalList.isEmpty()){
                return productList;
            }else{
                throw new ProductNotFoundException("No product found for requested Quantity!!");
            }

        }else {
            throw new ProductNotFoundException("No product found for category requested!!");
        }
    }


}
