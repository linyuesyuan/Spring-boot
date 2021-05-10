package com.example.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity 
@Data
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //IDENTITY：代表由資料庫控制
    //AUTO：代表由Spring boot 應用程式統一控制
    private long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "標題不能為空")
    private String title;

    @Column(columnDefinition = "enum('圖', '圖文', '文')")
    private String type;

    private Boolean available = Boolean.FALSE;

    @Size(min = 0, max = 20)
    private String keyword;

    @Size(max = 255)
    private String description;

    @Column(nullable = false)
    private String body;

    @Transient
    private List keywordlists;

    private List getKeywordlists(){
        return Arrays.asList(this.keyword.trim().split("|"));
    }

    public void setKeywordlist(List keywordlists){
        this.keywordlists = keywordlists;
    }
}
