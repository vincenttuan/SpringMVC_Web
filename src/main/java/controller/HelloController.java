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
import org.springframework.web.servlet.ModelAndView;
import vo.BMI;

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
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/save?username=john&password=1234
    @RequestMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "hello";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/calcBMI?height=170.1&weight=60.5
    @RequestMapping("/calcBMI")
    public String calcBMI(BMI bmi) {
        double h = bmi.getHeight();
        double w = bmi.getWeight();
        double value = w / Math.pow(h/100, 2);
        bmi.setValue(value);
        System.out.println(bmi);
        return "show_bmi";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/calcBMI2?height=170.1&weight=60.5
    @RequestMapping("/calcBMI2")
    public ModelAndView calcBMI2(BMI bmi) {
        double h = bmi.getHeight();
        double w = bmi.getWeight();
        double value = w / Math.pow(h/100, 2);
        bmi.setValue(value);
        System.out.println(bmi);
        ModelAndView mv = new ModelAndView();
        mv.addObject("bmi", bmi);
        mv.setViewName("show_bmi");
        return mv;
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/calcBMI3?height=170.1&weight=60.5
    @RequestMapping("/calcBMI3")
    public String calcBMI3(Map<String, Object> map, BMI bmi) {
        double h = bmi.getHeight();
        double w = bmi.getWeight();
        double value = w / Math.pow(h/100, 2);
        bmi.setValue(value);
        
        map.put("bmi", bmi);
        
        return "show_bmi";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/calcBMI4?height=170.1&weight=60.5
    @RequestMapping("/calcBMI4")
    public String calcBMI4(Model model, BMI bmi) {
        double h = bmi.getHeight();
        double w = bmi.getWeight();
        double value = w / Math.pow(h/100, 2);
        bmi.setValue(value);
        
        model.addAttribute("bmi", bmi);
        
        return "show_bmi";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/control/calcBMI5?height=170.1&weight=60.5
    @RequestMapping("/calcBMI5")
    public String calcBMI5(BMI bmi, HttpSession session) {
        double h = bmi.getHeight();
        double w = bmi.getWeight();
        double value = w / Math.pow(h/100, 2);
        bmi.setValue(value);
        
        session.setAttribute("bmi", bmi);
        
        return "show_bmi";
    }
    
    
}
