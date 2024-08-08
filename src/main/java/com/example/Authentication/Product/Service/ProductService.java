package com.example.Authentication.Product.Service;

import com.example.Authentication.Product.Model.UserProduct;
import com.example.Authentication.Product.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public UserProduct addProduct(UserProduct userProduct){
        return productRepo.save(userProduct);
    }
}
