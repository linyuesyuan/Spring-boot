package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "ec", indexStoreType = "product", replicas = 0, shards = 5)
@Data
public class Product implements Serializable {
    @Id
    private Long id;
    //ik_max_word 使用IK斷詞器
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    public Product(){ }

    public Product(Long id, String name, String category, Double price, String images, String body) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.images = images;
        this.body = body;
    }

    public Product(String name, String category, Double price, String images, String body) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.images = images;
        this.body = body;
    }

    //分類
    @Field(type = FieldType.Keyword) //儲存時不會對category斷詞
    private String category;

    //價格
    @Field(type = FieldType.Double)
    private Double price;
    //index = false 不建立索引
    @Field(index = false, type = FieldType.Keyword)
    private String images;
    private String body;
}
