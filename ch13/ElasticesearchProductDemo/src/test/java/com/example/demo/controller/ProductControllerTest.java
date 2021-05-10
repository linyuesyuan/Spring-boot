package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ProductControllerTest {
    private Integer PAGESIZE = 10;
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void save(){
        long id = System.currentTimeMillis();
        Product product = new Product(id, "冰與火之歌", "美劇", 7000.00, "/img/p1.jpg", "一齣世界觀宏大的美國戲劇");
//        Product product = new Product(id, "紅富士", "水果", 7.99, "/img/p1.jpg", "這是一個測試商品");
//        Product product = new Product(id, "蘋果", "水果", 6.00, "/img/p1.jpg", "蘋果，常見的蘋果");
        productRepository.save(product);
        System.out.println(product.getId());
    }
    @Test
    public void saveMultiData(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("嘎啦", "水果", 9.23, "/img/p1.jpg", "嘎啦，蘋果中一種好吃的粉蘋果"));
        products.add(new Product("桑薩", "水果", 9.23, "/img/p1.jpg", "桑薩，蘋果中一種好吃的粉蘋果"));
        products.add(new Product("紅將軍", "水果", 9.23, "/img/p1.jpg", "紅將軍，蘋果中一種好吃的粉蘋果"));
        products.add(new Product("金晶", "水果", 9.23, "/img/p1.jpg", "金晶，蘋果中一種好吃的粉蘋果"));
        products.add(new Product("金冠", "水果", 9.23, "/img/p1.jpg", "金冠，蘋果中一種好吃的粉蘋果"));
        products.add(new Product("蘋果8", "手機", 300.00, "/img/p1.jpg", "apple 8"));
        products.add(new Product("蘋果XS", "手機", 300.00, "/img/p1.jpg", "apple XS"));
        products.add(new Product("蘋果XR", "手機", 300.00, "/img/p1.jpg", "apple XR"));
        products.add(new Product("蘋果XS手機殼", "手機配件", 300.00, "/img/p1.jpg", "蘋果XS手機殼，可愛少女"));
        products.add(new Product("蘋果XR手機殼", "手機配件", 300.00, "/img/p1.jpg", "蘋果XR手機殼，普通版，ABS塑料材質"));
        for (Product product : products){
            product.setId(System.currentTimeMillis());
            productRepository.save(product);
            System.out.println(product);
        }
    }
    @Test
    public void getProduct(){
        Product product = productRepository.findByName("紅富士");
        System.out.println(product.getId());
    }
    @Test
    public void update(){
        long id = 1620468317611L;
        Product product = new Product(id, "金帥", "水果", 9.99, "/img/p1.jpg", "金帥也和紅富士一樣，非常好吃，脆脆的");
        productRepository.save(product);
    }
    @Test
    public void getProductById(){
        Product product = productRepository.findById(1620462686113L);
        System.out.println(product.getName() + product.getBody());
    }
    @Test
    public void delete(){
        long id = 1620462686113L;
        productRepository.deleteById(id);
    }
    @Test
    public void getALL(){
        Iterable<Product> list = productRepository.findAll(Sort.by("id").ascending());
        for (Product product : list){
            System.out.println(product);
        }
    }
}
