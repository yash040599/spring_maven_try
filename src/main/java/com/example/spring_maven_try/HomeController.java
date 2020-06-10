
package com.example.spring_maven_try;

import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    //@GetMapping("/")
    @RequestMapping("/") //Both works GET and Request
    public String home(){
        return "Welcome to my Spring Boot API";
    }
    
}
