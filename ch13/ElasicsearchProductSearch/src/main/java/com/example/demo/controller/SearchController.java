package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("search")
    public ModelAndView searchByPageAndSort(Integer start, String key){
        if (start == null){
            start = 0;
        }

        int size = 5;
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(key, "name", "body"));
        nativeSearchQueryBuilder.withPageable(PageRequest.of(start, size));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("page", products);
        modelAndView.addObject("key", key);
        return modelAndView;
    }
}
