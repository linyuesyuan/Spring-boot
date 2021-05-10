package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.apache.lucene.search.join.QueryBitSetProducer;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void queryByPriceBetween(){
        Iterable<Product> list = productRepository.findByPriceBetween(5, 8);
        for (Product product : list){
            System.out.println(product);
        }
    }
    @Test
    public void termQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("name", "富"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void termsQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termsQuery("name", "富", "帥"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void matchQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", "紅士"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void multiMatchQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("紅富士金帥", "name", "body"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void queryStringQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.queryStringQuery("我覺得紅富士好吃").field("name"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void prefixQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.prefixQuery("name", "士"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void wildcardQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.wildcardQuery("name", "金*"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void wildcardQuery2(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.wildcardQuery("name", "金?"));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void fuzzyQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.fuzzyQuery("name", "士").fuzziness(Fuzziness.ONE));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void moreLikeThisQuery(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.moreLikeThisQuery(new String[] {"name"}, new String[] {"紅"}, null));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        for (Product product : products){
            System.out.println(product);
        }
    }
    @Test
    public void multiMoreLikeThisQuery() {
        NativeSearchQueryBuilder nativeSearchQueryBuilderQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.moreLikeThisQuery(new String[]{"name"}, new String[]{"紅"}, null));
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.moreLikeThisQuery(new String[]{"name"}, new String[]{"金"}, null));
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.moreLikeThisQuery(new String[]{"name"}, new String[]{"紅"}, null));
        Page<Product> products = productRepository.search(nativeSearchQueryBuilderQueryBuilder.build());
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
