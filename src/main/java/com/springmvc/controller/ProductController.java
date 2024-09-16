package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.dao.ProductDAO;
import com.springmvc.entity.Product;

@Controller
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/home")
    public String viewHomePage() {
        return "home"; // Shows the form to add a product
    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> productList = productDAO.getAllProducts();
        model.addAttribute("products", productList);
        return "products"; // Shows the table with all products
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product p,RedirectAttributes redirectAttributes) {
       // Product product = new Product();
        productDAO.saveProduct(p);
        redirectAttributes.addFlashAttribute("msg","Product Added Successfully");
        return "redirect:/home"; // Redirects back to the form page after adding a product
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") int id) {
        productDAO.deleteProduct(id);
        return "redirect:/products"; // Redirects back to the product list page after deleting a product
    }
}