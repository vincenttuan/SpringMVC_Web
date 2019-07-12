package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import stock.orm.dao.IFundDao;
import stock.orm.dao.IStockDao;
import stock.orm.model.Fund;
import stock.orm.model.FundNet;
import stock.orm.model.Stock;

@Controller
@RequestMapping("/stock_controller")
public class StockController {
    
    private IStockDao stockDao;
    private IFundDao fundDao;
    
    private Gson gson = new GsonBuilder()     
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    
    // 自動綁定
    @Autowired
    public void setStockDao(IStockDao stockDao) {
        this.stockDao = stockDao;
    }
    
    @Autowired
    public void setFundDao(IFundDao fundDao) {
        this.fundDao = fundDao;
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/stock_controller/add/stock?stockCode=2303&stockName=聯電
    @RequestMapping(value = "/add/stock")
    @ResponseBody
    public String addStock(@RequestParam String stockCode, @RequestParam String stockName) {
        stockDao.create(new Stock(stockCode, stockName));
        return "Add Stock ok";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/stock_controller/add/fund?fundName=A&fundDesc=科技&value=10
    @RequestMapping(value = "/add/fund")
    @ResponseBody
    public String addFund(@RequestParam String fundName, @RequestParam String fundDesc, @RequestParam int value) {
        // 建立新基金
        Fund fund = new Fund(fundName, fundDesc);
        // 建立基金淨值
        FundNet fundNet = new FundNet();
        fundNet.setValue(value);
        fundNet.setFund(fund);
        fund.setFundNet(fundNet);
        fundDao.create(fund);
        return "Add Fund ok";
    }
    
    
    // http://localhost:8080/SpringMVC_Web/mvc/stock_controller/query/stock
    @RequestMapping(value = "/query/stock", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryStock() {
        List<Stock> list = stockDao.queryAll(Stock.class);
        return gson.toJson(list);
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/stock_controller/query/fund
    @RequestMapping(value = "/query/fund", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryFund() {
        List<Fund> list = fundDao.queryAll(Fund.class);
        return gson.toJson(list);
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/stock_controller/update/stock/6?stockCode=3008&stockName=大立光
    @RequestMapping(value = "/update/stock/{id}")
    @ResponseBody
    public String updateStock(@PathVariable(value = "id") Integer id, @RequestParam String stockCode, @RequestParam String stockName) {
        Stock stock = stockDao.get(Stock.class, id);
        if(stock == null) {
            return "Update Error ! (Not found)";
        }
        stock.setStockCode(stockCode);
        stock.setStockName(stockName);
        stockDao.update(stock);
        return "Update Stock ok";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/stock_controller/delete/stock/7
    @RequestMapping(value = "/delete/stock/{id}")
    @ResponseBody
    public String deleteStock(@PathVariable(value = "id") Integer id) {
        Stock stock = stockDao.get(Stock.class, id);
        if(stock == null) {
            return "Delete Error ! (Not found)";
        }
        stockDao.delete(stock);
        return "Delete Stock ok";
    }
    
    // http://localhost:8080/SpringMVC_Web/mvc/stock_controller/get/stock/6
    @RequestMapping(value = "/get/stock/{id}", produces="application/json;charset=utf-8")
    @ResponseBody
    public String getStock(@PathVariable(value = "id") Integer id) {
        Stock stock = stockDao.get(Stock.class, id);
        if(stock == null) {
            return "Get Error ! (Not found)";
        }
        Gson gson = new GsonBuilder()     
            .excludeFieldsWithoutExposeAnnotation()
            .create();
        return gson.toJson(stock);
    }
    
}
