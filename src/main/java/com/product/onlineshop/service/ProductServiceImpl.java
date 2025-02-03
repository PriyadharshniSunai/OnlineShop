package com.product.onlineshop.service;

import com.product.onlineshop.client.DemoClient;
import com.product.onlineshop.entity.Product;
import com.product.onlineshop.exception.ProductNotFoundException;
import com.product.onlineshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private DemoClient democlient;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,DemoClient democlient ){
        this.productRepository=productRepository;
        this.democlient=democlient;
    }

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
