package com.example.Authentication.Product.Controller;

import com.example.Authentication.Product.Model.UserProduct;
import com.example.Authentication.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public UserProduct addProduct(@RequestBody UserProduct userProduct){
        return productService.addProduct(userProduct);
    }
}
