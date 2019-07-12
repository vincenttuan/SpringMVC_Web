package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stock_controller")
public class StockController {
    
    @RequestMapping(value = "/queryStock", method = RequestMethod.GET)
    @ResponseBody
    public String queryStock() {
        return "queryStock";
    }
    
}
