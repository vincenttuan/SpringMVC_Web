package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/control")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        return "hello";
    }
    
    @RequestMapping(value = "/hello2", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello2() {
        System.out.println("Hello World 2");
        return "hello";
    }
}
