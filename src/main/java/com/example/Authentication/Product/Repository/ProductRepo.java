package com.example.Authentication.Product.Repository;

import com.example.Authentication.Product.Model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<UserProduct, Integer> {

}
