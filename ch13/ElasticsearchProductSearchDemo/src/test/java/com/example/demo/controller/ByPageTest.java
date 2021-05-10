package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest
public class ByPageTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void termQuery(){
        int page = 1;
        int size = 7;
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("body", "蘋"));
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page, size));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void searchByPageAndSort(){
        int page = 1;
        int size = 5;
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("body", "蘋"));
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page, size));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
}
