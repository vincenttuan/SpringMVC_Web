package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        return "hello";
    }
}
