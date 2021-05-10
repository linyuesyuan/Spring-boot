package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTypeTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void string(){
        redisTemplate.opsForValue().set("num", 123);
        redisTemplate.opsForValue().set("string", "some string");
        Object s = redisTemplate.opsForValue().get("num");
        Object s2 = redisTemplate.opsForValue().get("string");
        System.out.println(s);
        System.out.println(s2);
    }

    @Test
    public void string2(){
        redisTemplate.opsForValue().set("num", "123XYZ", 3, TimeUnit.SECONDS);
        try{
            Object s = redisTemplate.opsForValue().get("num");
            System.out.println(s);
            Thread.currentThread().sleep(2000);
            Object s2 = redisTemplate.opsForValue().get("num");
            System.out.println(s2);
            Thread.currentThread().sleep(5000);
            Object s3 = redisTemplate.opsForValue().get("num");
            System.out.println(s3);
        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    @Test
    public void string3(){
        redisTemplate.opsForValue().set("key", "hello world", 6);
        System.out.println(redisTemplate.opsForValue().get("key"));
    }

    @Test
    public void getAndSet(){
        redisTemplate.opsForValue().set("getSetTest", "test");
        System.out.println(redisTemplate.opsForValue().getAndSet("getSetTest", "test2"));
        System.out.println(redisTemplate.opsForValue().get("getSetTest"));
    }

    @Test
    public void string5(){
        redisTemplate.opsForValue().append("k", "test");
        System.out.println(redisTemplate.opsForValue().get("k"));
        redisTemplate.opsForValue().append("k", "test2");
        System.out.println(redisTemplate.opsForValue().get("k"));
    }

    @Test
    public void string6(){
        redisTemplate.opsForValue().set("key", "1");
        System.out.println(redisTemplate.opsForValue().size("key"));
        System.out.println(redisTemplate.opsForValue().get("key"));
        redisTemplate.opsForValue().getAndSet("key", "12345678");
        System.out.println(redisTemplate.opsForValue().get("key"));
        System.out.println(redisTemplate.opsForValue().size("key"));
    }

    @Test
    public void hash1(){
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("name", "yue");
        testMap.put("sex", "female");
        redisTemplate.opsForHash().putAll("Hash", testMap);
        System.out.println(redisTemplate.opsForHash().entries("Hash"));
    }

    @Test
    public void hash2(){
        redisTemplate.opsForHash().put("redisHash", "name", "yue");
        redisTemplate.opsForHash().put("redisHash", "sex", "female");
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        System.out.println(redisTemplate.opsForHash().values("redisHash"));
    }

    @Test
    public void hash3(){
        redisTemplate.opsForHash().put("redisHash", "name", "yue");
        redisTemplate.opsForHash().put("redisHash", "sex", "female");
        System.out.println(redisTemplate.opsForHash().delete("redisHash", "name"));
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
    }

    @Test
    public void hash4(){
        redisTemplate.opsForHash().put("redisHash", "name", "yue");
        redisTemplate.opsForHash().put("redisHash", "sex", "female");
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash", "name"));
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash", "age"));
    }

    @Test
    public void hash7(){
        redisTemplate.opsForHash().put("redisHash", "name", "yue");
        redisTemplate.opsForHash().put("redisHash", "sex", "female");
        System.out.println(redisTemplate.opsForHash().get("redisHash", "name"));
    }

    @Test
    public void hash8(){
        redisTemplate.opsForHash().put("redisHash", "name", "yue");
        redisTemplate.opsForHash().put("redisHash", "sex", "female");
        System.out.println(redisTemplate.opsForHash().keys("redisHash"));
    }

    @Test
    public void hash9(){
        redisTemplate.opsForHash().put("redisHash", "name", "yue");
        redisTemplate.opsForHash().put("redisHash", "sex", "female");
        System.out.println(redisTemplate.opsForHash().size("redisHash"));
    }

    @Test
    public void list1(){
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().leftPushAll("list", strings);
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
    }

    @Test
    public void list2(){
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().leftPushAll("list", strings);
        System.out.println(redisTemplate.opsForList().size("list"));
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
//        redisTemplate.delete("list");
//        result:
//        3
//        [3, 2, 1]
    }

    @Test
    public void list3(){
        for (int i = 0; i < 3; i++) {
            redisTemplate.opsForList().leftPush("list", i);
            System.out.println(redisTemplate.opsForList().size("list"));
            System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        }
    }

    @Test
    public void list4(){
        for (int i = 1; i < 4; i++) {
            redisTemplate.opsForList().rightPush("listRight", i);
            System.out.println(redisTemplate.opsForList().size("listRight"));
            System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        }
    }

    @Test
    public void list5(){
        redisTemplate.delete("list");
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list", strings);
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
    }

    @Test
    public void list6(){
//        redisTemplate.delete("list6");
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list6", strings);
        System.out.println(redisTemplate.opsForList().range("list6", 0, -1));
        redisTemplate.opsForList().set("list6", 1, "testSet");
        System.out.println(redisTemplate.opsForList().range("list6", 0, -1));
    }

    @Test
    public void list7(){
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list7", strings);
        System.out.println(redisTemplate.opsForList().range("list7", 0, -1));
        redisTemplate.opsForList().remove("list7", 0, "1");
        System.out.println(redisTemplate.opsForList().range("list7", 0, -1));
    }

    @Test
    public void list8(){
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list8", strings);
        System.out.println(redisTemplate.opsForList().range("list8", 0, -1));
        System.out.println(redisTemplate.opsForList().index("list8", 7));
    }

    @Test
    public void list9(){
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("list9", strings);
        System.out.println(redisTemplate.opsForList().range("list9", 0, -1));
        System.out.println(redisTemplate.opsForList().leftPop("list9"));
        System.out.println(redisTemplate.opsForList().range("list9", 0, -1));
    }

    @Test
    public void list10(){
        String[] strings = new String[]{"1", "2", "3", "4", "5", "6"};
        redisTemplate.opsForList().rightPushAll("list10", strings);
        System.out.println(redisTemplate.opsForList().range("list10", 0, -1));
        System.out.println(redisTemplate.opsForList().rightPop("list10"));
        System.out.println(redisTemplate.opsForList().range("list10", 0, -1));
    }

    @Test
    public void Set1(){
        String[] strings = new String[]{"str1", "str2", "str3"};
        System.out.println(redisTemplate.opsForSet().add("Set1", strings));
        System.out.println(redisTemplate.opsForSet().add("Set1", "1", "2", "3"));
    }

    @Test
    public void Set2(){
        String[] strings = new String[]{"str1", "str2", "str3"};
        String[] strings2 = new String[]{"str1", "str3"};
        System.out.println(redisTemplate.opsForSet().add("Set2", strings));
        System.out.println(redisTemplate.opsForSet().members("Set2"));
        System.out.println(redisTemplate.opsForSet().remove("Set2", strings2));
        System.out.println(redisTemplate.opsForSet().members("Set2"));
    }

    @Test
    public void Set3(){
        String[] strings = new String[]{"str1", "str2", "str3"};
        System.out.println(redisTemplate.opsForSet().add("Set3", strings));
        System.out.println(redisTemplate.opsForSet().pop("Set3"));
        System.out.println(redisTemplate.opsForSet().members("Set3"));
    }

    @Test
    public void Set4(){
        String[] strings = new String[]{"str1", "str2", "str3"};
        System.out.println(redisTemplate.opsForSet().add("Set4", strings));
        redisTemplate.opsForSet().move("Set4", "str2", "Set4to2");
        System.out.println(redisTemplate.opsForSet().members("Set4"));
        System.out.println(redisTemplate.opsForSet().members("Set4to2"));
    }

    @Test
    public void Set5(){
        String[] strings = new String[]{"str1", "str2", "str3"};
        System.out.println(redisTemplate.opsForSet().add("Set5", strings));
        System.out.println(redisTemplate.opsForSet().size("Set5"));
    }

    @Test
    public void Set6(){
        String[] strings = new String[]{"str1", "str2", "str3"};
        System.out.println(redisTemplate.opsForSet().add("Set6", strings));
        System.out.println(redisTemplate.opsForSet().members("Set6"));
    }

    @Test
    public void Set7(){
        String[] strings = new String[]{"str1", "str2", "str3"};
        System.out.println(redisTemplate.opsForSet().add("Set7", strings));
        Cursor<Object> cursor = redisTemplate.opsForSet().scan("Set7", ScanOptions.NONE);
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

    @Test
    public void Zset1(){
        redisTemplate.delete("zset1");
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 9.9);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 9.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        System.out.println(redisTemplate.opsForZSet().add("zset1", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1", 0, -1));
    }

    @Test
    public void Zset2(){
        System.out.println(redisTemplate.opsForZSet().add("zset2", "zset-1", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset2", "zset-1", 1.0));
    }

    @Test
    public void Zset3(){
        System.out.println(redisTemplate.opsForZSet().add("zset3", "zset-1", 1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset3", "zset-2", 1.0));
        System.out.println(redisTemplate.opsForZSet().range("zset3", 0, -1));
        System.out.println(redisTemplate.opsForZSet().remove("zset3", "zset-1"));
        System.out.println(redisTemplate.opsForZSet().range("zset3", 0, -1));
    }

    @Test
    public void Zset4(){
        System.out.println(redisTemplate.delete("zset4"));
        System.out.println(redisTemplate.opsForZSet().add("zset4", "zset-1", 1.1));
        System.out.println(redisTemplate.opsForZSet().add("zset4", "zset-2", 1.0));
        System.out.println(redisTemplate.opsForZSet().range("zset4", 0, -1));
        System.out.println(redisTemplate.opsForZSet().rank("zset4", "zset-1"));
    }

    @Test
    public void Zset5(){
        redisTemplate.delete("zset5");
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 9.9);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 9.0);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        System.out.println(redisTemplate.opsForZSet().add("zset5", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset5", 0, -1));
    }

    @Test
    public void Zset6(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        System.out.println(redisTemplate.opsForZSet().add("zset6", tuples));
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset6", 0, 9));
        System.out.println(redisTemplate.opsForZSet().count("zset6", 0, 5));
    }

    @Test
    public void Zset7(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        System.out.println(redisTemplate.opsForZSet().add("zset7", tuples));
        System.out.println(redisTemplate.opsForZSet().size("zset7"));
    }

    @Test
    public void Zset8(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        System.out.println(redisTemplate.opsForZSet().add("zset8", tuples));
        System.out.println(redisTemplate.opsForZSet().score("zset8", "zset-2"));
    }

    @Test
    public void Zset9(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        System.out.println(redisTemplate.opsForZSet().add("zset9", tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset9", 0, -1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset9", 1, 2));
        System.out.println(redisTemplate.opsForZSet().range("zset9", 0, -1));
    }

    @Test
    public void Zset10(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 3.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 4.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3", 5.7);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4", 1.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        System.out.println(redisTemplate.opsForZSet().add("zset10", tuples));
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("zset10", ScanOptions.NONE);
        while (cursor.hasNext()){
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue() + ": " + item.getScore());
        }
    }
}
