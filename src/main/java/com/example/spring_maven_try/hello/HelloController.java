package com.example.spring_maven_try.hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Defines that this is a REST controller
@CrossOrigin //Works without this for now
public class HelloController {
    @GetMapping("/hello") //This is a GET request that says run this method when /hello is performed
    public String sayhi(){
        return "Ohh Hello!";
    }
    
}