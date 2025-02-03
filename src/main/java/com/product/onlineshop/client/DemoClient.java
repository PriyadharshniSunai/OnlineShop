package com.product.onlineshop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "demo", url = "http://localhost:8081", path = "/")
public interface DemoClient {

    @GetMapping("/Message")
    public String getMessage();

    @GetMapping("/Message1")
    public String getMessage1();
}
