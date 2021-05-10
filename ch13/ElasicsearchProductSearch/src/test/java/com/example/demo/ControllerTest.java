package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest
public class ControllerTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void test(){
        int start = 0;
        int size = 5;
        String key = "蘋果";
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(key, "name", "body"));
        nativeSearchQueryBuilder.withPageable(PageRequest.of(start, size));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
}
