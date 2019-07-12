package controller;

import com.google.gson.Gson;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import stock.orm.dao.IStockDao;
import stock.orm.model.Stock;

@Controller
@RequestMapping("/stock_controller")
public class StockController {
    
    private IStockDao stockDao;
    
    // 自動綁定
    @Autowired
    public void setStockDao(IStockDao stockDao) {
        this.stockDao = stockDao;
    }
    
    @RequestMapping(value = "/add/stock")
    @ResponseBody
    public String addStock(@RequestParam String stockCode, @RequestParam String stockName) {
        stockDao.create(new Stock(stockCode, stockName));
        return "addStock";
    }
    
    @RequestMapping(value = "/query/stock", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryStock() {
        List<Stock> list = stockDao.queryAll(Stock.class);
        return new Gson().toJson(list);
    }
    
}
