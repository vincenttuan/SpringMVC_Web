package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "get";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String post() {
        return "post";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public String put() {
        return "put";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete() {
        return "delete";
    }
    
}
