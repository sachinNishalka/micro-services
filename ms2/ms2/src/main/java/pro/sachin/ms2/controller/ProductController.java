package pro.sachin.ms2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import pro.sachin.ms2.dto.Coupon;
import pro.sachin.ms2.model.Product;
import pro.sachin.ms2.repository.ProductRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository repository;

    private final RestTemplate restTemplate;


    @GetMapping("/product")
    public List<Product> getProduct() {

        return repository.findAll();
    }

    @PostMapping("/product/")
    public Product addProduct(@RequestBody Product product) {
        Coupon coupon = restTemplate.getForObject("http://localhost:8080/coupon/"+product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repository.save(product);
    }
}
