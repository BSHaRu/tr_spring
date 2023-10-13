package com.example.security1.controller;

import com.example.security1.config.security.PrincipalDetails;
import com.example.security1.entity.User2Entity;
import com.example.security1.repository.User2Repository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class MainController {

    /* viewResolvers 기본적으로 생략해도 되지만 배포할 때는 필요한거 같음
        - prefix : classpath:/templates/
        - suffix : .html
     */

    @Autowired
    private User2Repository user2Repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/test/login")
    public @ResponseBody String loginTest(Authentication authentication){
        log.info("test login ======================");
        PrincipalDetails principalDetails
                = (PrincipalDetails) authentication.getPrincipal();
        log.info("authentication : " + principalDetails.getUser2Entity());
        return "세션 정보 확인";
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }
    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String register(User2Entity user2Entity){
        log.info("register user :" + user2Entity);
        user2Entity.setRole("USER");
        String pass = user2Entity.getPass();
        String encodePass = encoder.encode(pass);
        user2Entity.setPass(encodePass);
        user2Repository.save(user2Entity);
        return "redirect:/login";
    }

    @Secured("ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }
}
