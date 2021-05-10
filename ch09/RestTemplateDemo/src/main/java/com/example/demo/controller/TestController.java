package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {
    @RequestMapping(value = "/getParameter", method = RequestMethod.GET)
    public User getParameter(User user){
        return user;
    }

    @RequestMapping(value = "/getUser1", method = RequestMethod.GET)
    public User user1(){
        return new User(1, "yue");
    }

    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    public User postUser(User user){
        System.out.println("name: " + user.getName());
        System.out.println("id: " + user.getId());
        return user;
    }

    @RequestMapping(path = "success")
    public String loginSuccess(String name){
        return "welcome" + name;
    }

    @RequestMapping(path = "post", method = RequestMethod.POST)
    public String post(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "password", required = false) String password,
                       @RequestParam(value = "id", required = false) Integer id){
        String uri = "redirect:/success?name=" + name + "&id=" + id + "&status=success";
        response.addHeader("Location", uri);
        return uri;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User put(@PathVariable("id") int id, @RequestParam(value = "name", required = false) String name){
        return new User(id, name);
    }
}
