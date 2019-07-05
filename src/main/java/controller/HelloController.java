package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/control")
public class HelloController {
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/hello
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        return "hello";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/hello2
    @RequestMapping(value = "/hello2", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello2() {
        System.out.println("Hello World 2");
        return "hello";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/get/77
    @RequestMapping("/get/{id}")
    public String get(@PathVariable(value = "id") Integer id) {
        System.out.println("id: " + id);
        return "redirect:../hello";
        //return "hello";
    }
    
    /*
    @RequestMapping(value = "路徑")
    ?  匹配 1 個字符     /testPaths?       -> /testPathsA
    ?? 匹配 2 個字符     /testPaths??      -> /testPathsAA
    *  匹配任意字符      /testPaths/*       -> /testPathsABCDEFG
    *  匹配任意字符      /testPaths/ * /ok  -> /testPaths/ttt/ok
    ** 可含有任意多層路徑 /testPaths/**      -> /testPaths/aa/bb/cc
    ** 可含有任意多層路徑 /testPaths/ ** /ok -> /testPaths/aa/bb/cc/ok
    */
    // http://localhost:8080/SpringMVC_Web/mvc/control/testPaths
    @RequestMapping(value = "/testPaths")
    public String testPaths() {
        return "hello";
    }
}
