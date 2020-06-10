package com.example.spring_maven_try.info;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping("/info")
    public List<info> sayinfo(){

        return Arrays.asList(
            new info("Yash Agrawal", "Times Internet", "c-yash.agarwal@timesinternet.in"),
            new info("Yash Agrawal", "Civil Engineering Association", "yash.ceagsec@gmail.com"),
            new info("Yash Agrawal", "Power Anser", "yash040599@gmail.com"),
            new info("Yash Agrawal", "Consulting Engineers Group", "yash040599@gmail.com")
        );
    }
    
}