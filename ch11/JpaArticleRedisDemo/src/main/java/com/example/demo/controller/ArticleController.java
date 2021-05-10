package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/{id}")
    public ModelAndView testPathVariable(@PathVariable("id") Integer id) throws Exception{
        Article article = articleService.findArticleById(id);
        if (article.getView() >0 ){
            stringRedisTemplate.boundValueOps("name::" + id).increment(article.getView() +1 );
            Long view = Long.parseLong(stringRedisTemplate.opsForValue().get("name::"+id));
            article.setView(view);
        }else {
            stringRedisTemplate.boundValueOps("name::" + id).increment(1);
        }
        ModelAndView mav = new ModelAndView("/article/show");
        mav.addObject("article", article);
        return mav;
    }

    @GetMapping("/add")
    public String addArticle()   throws Exception{
        return "article/add";
    }

    @PostMapping("")
    public String saveArticle(Article model)  throws Exception{
        articleRepository.save(model);
        return "redirect:/article/";
    }

    @DeleteMapping("/{id}")
    public String del(@PathVariable("id") long id)  throws Exception{
        articleRepository.deleteById(id);
        return "redirect:";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editArticle(@PathVariable("id") long id)  throws Exception {
        Article model = articleRepository.findArticleById(id);
        ModelAndView mav = new ModelAndView("article/edit");
        mav.addObject("article", model);
        return mav;
    }


    @PutMapping("/{id}")
    public String editArticleSave(Article model, long id)  throws Exception{
        model.setId(id);
        articleRepository.save(model);
        return "redirect:";
    }

    @RequestMapping("")
    public ModelAndView articleList(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                    @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        start = start < 0 ? 0 : start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, limit, sort);
        Page<Article> page = articleRepository.findAll(pageable);
        ModelAndView mav = new ModelAndView("article/list");
        mav.addObject("page", page);
        return mav;
    }
}
