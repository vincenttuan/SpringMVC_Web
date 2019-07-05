package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/login?username=Java
    @RequestMapping(value = "/login")
    public String login(@RequestParam String username) {
        System.out.println("login: " + username);
        return "hello";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/login2?realname=Java
    @RequestMapping(value = "/login2")
    public String login2(@RequestParam(name = "realname", defaultValue = "spring", required = false) String username) {
        System.out.println("login: " + username);
        return "hello";
    }
    
    /*
        params = {"name", "age"}
        params = {"name", "age!=10"}
        params = {"!name", "age"}
        params = {"name=aaa", "age=10"}
    */
    // http://localhost:8080/SpringMVC_Web/mvc/control/testArgs?name=aaa&age=10
    @RequestMapping(value = "/testArgs", params = {"name", "age"})
    public String testArgs(String name, Integer age) {
        System.out.println(name + ", " + age);
        return "hello";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/testCookie
    @RequestMapping("/testCookie")
    public String testCookie(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("sessionId: " + sessionId);
        return "hello";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/testHeader
    @RequestMapping("/testHeader")
    public String testHeader(@RequestHeader(value = "User-Agent") String userAgent) {
        System.out.println("userAgent: " + userAgent);
        return "hello";
    }
    
}
