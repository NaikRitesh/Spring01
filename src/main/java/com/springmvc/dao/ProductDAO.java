package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.Product;

public interface ProductDAO {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    void deleteProduct(int id);
}