package com.ahmed.springrest.myproductsrestapi.controller;

import com.ahmed.springrest.myproductsrestapi.dto.Voucher;
import com.ahmed.springrest.myproductsrestapi.model.Product;
import com.ahmed.springrest.myproductsrestapi.repository.ProductRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/productsapi")
public class ProductRestController {

    @Autowired
    ProductRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${voucherService.url}")
    private String voucherServiceURL;

    @PostMapping("/products/")
    public Product createProduct(@RequestBody Product product) {

        Voucher voucher = restTemplate.getForObject(voucherServiceURL + product.getVoucherCode(),
                Voucher.class);

        product.setPrice(product.getPrice().subtract(voucher.getDiscount()));

        return repository.save(product);
    }

    // @GetMapping("/products/")
    // public List<Product> getProducts() {
    // return repository.findAll();
    // }

    // @GetMapping("/products/{id}")
    // public Product geProduct(@PathVariable("id") int id) {
    // return repository.findById(id).get();
    // }

    // @PutMapping("/products/")
    // public Product updateProduct(@RequestBody Product product) {
    // return repository.save(product);
    // }

    // @DeleteMapping("/products/{id}")
    // public void delteProduct(@PathVariable("id") int id) {
    // repository.deleteById(id);
    // }
}
