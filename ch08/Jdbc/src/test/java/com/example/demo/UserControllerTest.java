package com.example.demo;

import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void createUserTable() throws Exception{
        String sql = "CREATE TABLE `user` (\n" +
                "`id` int(10) NOT NULL AUTO_INCREMENT, \n" +
                "`username` varchar(100) DEFAULT NULL, \n" +
                "`password` varchar(100) DEFAULT NULL, \n" +
                "PRIMARY KEY(`id`))";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void saveUserTest() throws Exception{
        String sql = "INSERT INTO user (USERNAME, PASSWORD) VALUES ('test', '2324')";
        int rows = jdbcTemplate.update(sql);
        System.out.println(rows);
    }

    @Test
    public void getUserByName() throws Exception{
        String name = "jean";
        String sql = "SELECT * FROM user WHERE username = ?";
        List<User> list = jdbcTemplate.query(sql, new User(), new Object[]{name});
        for(User user : list){
            System.out.println(user);
        }
    }
    @Test
    public void list() throws Exception{
        String sql = "SELECT * FROM user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    public void updateUserPassword() throws Exception{
        Integer id = 1;
        String password = "999888";
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, password, id);
        System.out.println(rows);
    }

    @Test
    public void deleteUserById() throws Exception{
        String sql = "DELETE FROM user WHERE id = ?";
        int rows = jdbcTemplate.update(sql, 4);
        System.out.println(rows);
    }
}
