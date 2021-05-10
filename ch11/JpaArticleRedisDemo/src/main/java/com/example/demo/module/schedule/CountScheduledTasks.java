package com.example.demo.module.schedule;

import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CountScheduledTasks {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(cron = "0002 ? * * ")
    public void syncPostViews(){
        Long startTime = System.nanoTime();
        List dtoList = new ArrayList<>();
        Set<String> keySet = stringRedisTemplate.keys("name::*");
        for (String key : keySet){
            String views = stringRedisTemplate.opsForValue().get(key);
            String sid = key.replaceAll("name::", "");
            long lid = Long.parseLong(sid);
            long lviews = Long.parseLong(views);
            articleRepository.updateArticleViewById(lviews, lid);
            stringRedisTemplate.delete(key);
        }

    }
}
