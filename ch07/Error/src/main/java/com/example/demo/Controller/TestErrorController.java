package com.example.demo.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("${server.error.path:${error.path:/error}}")
@RequestMapping(value = "/error")
public class TestErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }
    @RequestMapping
    public Map<String, Object> handleError(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 404);
        map.put("msg", "不存在");
        return map;
    }

    @RequestMapping("/OK")
    @ResponseBody
    public Map<String, Object> noError(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("msg", "正常，這是測試頁面");
        return map;
    }

    @RequestMapping(value = "", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String errorHtml4040 (HttpServletRequest request, HttpServletResponse response){
        return "404 錯誤, 不存在";
    }

    @RequestMapping(value = "", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> errorJson(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 404);
        map.put("msg", "不存在");
        return map;
    }


}
