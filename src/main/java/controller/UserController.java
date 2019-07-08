package controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vo.BMI;

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
