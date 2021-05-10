package com.example.demo.config.authenticationHandler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof UserDetails){
             UserDetails user = (UserDetails) principal;
             System.out.println("username: " + user.getUsername() + ", password: " + user.getPassword());
             request.getSession().setAttribute("UserDetails", user);
             response.setContentType("application/json;charset=utf-8");
             PrintWriter out = response.getWriter();
             out.write("{\"status\": \"ok\", \"message\": \"登入成功\"}");
             out.flush();
             out.close();
        }
    }
}
