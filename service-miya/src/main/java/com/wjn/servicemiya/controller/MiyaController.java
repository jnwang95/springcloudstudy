package com.wjn.servicemiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-12-17 11:56
 */
@RestController
public class MiyaController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String home(){
        System.out.println("hi is being called");
        return "hi i'm miya!";
    }

    @RequestMapping("/miya")
    public String info(){
        System.out.println( "info is being called");
        return restTemplate.getForObject("http://localhost:8988/info",String.class);
    }


}
