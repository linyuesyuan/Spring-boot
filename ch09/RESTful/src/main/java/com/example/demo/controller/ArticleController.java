package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.result.ExceptionMsg;
import com.example.demo.result.Response;
import com.example.demo.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {
    protected Response response(ExceptionMsg msg){
        return new Response(msg);
    }

    protected Response result(ExceptionMsg msg){
        return new Response(msg);
    }

    @Autowired
    protected ArticleRepository articleRepository;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getArticleList(){
        List<Article> articleList = new ArrayList<Article>(articleRepository.findAll());
        return new ResponseData(ExceptionMsg.SUCCESS, articleList);
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseData add(@RequestParam("title") String title, @RequestParam("body") String body){
//        Article article = new Article();
//        article.setTitle(title);
//        article.setBody(body);
//        System.out.println("title: " + article.getTitle());
//        System.out.println("body: " + article.getBody());
//        articleRepository.save(article);
//        return new ResponseData(ExceptionMsg.SUCCESS, article);
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST) //傳送body格式選擇form-data
    public ResponseData add(Article article){
        System.out.println("title: " + article.getTitle());
        System.out.println("body: " + article.getBody());
        articleRepository.save(article);
        return new ResponseData(ExceptionMsg.SUCCESS, article);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") long id){
        articleRepository.deleteById(id);
        return result(ExceptionMsg.SUCCESS);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseData update(Article article){
        System.out.println("title: " + article.getTitle());
        System.out.println("body: " + article.getBody());
        articleRepository.save(article);
        return new ResponseData(ExceptionMsg.SUCCESS, article);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData findArticle(@PathVariable("id") Integer id) throws IOException {
        Article article = articleRepository.findArticleById(id);
        if(article != null){
            return new ResponseData(ExceptionMsg.SUCCESS, article);
        }
        return new ResponseData(ExceptionMsg.FAILED, article);
    }
}
