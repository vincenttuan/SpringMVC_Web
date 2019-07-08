package controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
    private static List<User> list = new ArrayList<>();
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "get:" + list;
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String post(User user) {
        list.add(user);
        return "post:" + user;
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public String put(User user) {
        int id = user.getNum().getId();
        list.stream()
                .filter(u -> u.getNum().getId() == user.getNum().getId())
                .forEach(u -> {
                    u.setUname(user.getUname());
                    u.setUage(user.getUage());
                });
        return "put:" + user;
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/user/
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(User user) {
        int id = user.getNum().getId();
        User del_user = list.stream().filter(u -> u.getNum().getId() == id).findFirst().get();
        list.remove(del_user);
        return "delete:" + user;
    }
    
}
