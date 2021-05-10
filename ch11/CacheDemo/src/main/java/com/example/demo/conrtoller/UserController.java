package com.example.demo.conrtoller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public void insertUser() throws Exception{
        User user = new User();
        user.setName("yue");
        userService.insertUser(user);
    }

    @GetMapping("/{id}")
    public void findUserById(@PathVariable long id) throws Exception{
        User user = userService.findUserById(id);
        System.out.println(user.getId() + ": " + user.getName());
    }

    @PutMapping("/{id}")
    public User updateUserById(User user){
        return userService.updateUserById(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id){
        userService.deleteUserById(id);
    }
}
