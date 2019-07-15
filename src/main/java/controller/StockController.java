package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import stock.orm.dao.IFundDao;
import stock.orm.dao.IInvestorDao;
import stock.orm.dao.IStockDao;
import stock.orm.dao.ITraderDao;
import stock.orm.model.Fund;
import stock.orm.model.FundNet;
import stock.orm.model.Investor;
import stock.orm.model.Stock;
import stock.orm.model.Trader;

@Controller
@RequestMapping("/stock_controller")
public class StockController {
    
    private IStockDao stockDao;
    private IFundDao fundDao;
    private ITraderDao traderDao;
    private IInvestorDao investorDao;
    
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
    
    @Autowired
    public void setTraderDao(ITraderDao traderDao) {
        this.traderDao = traderDao;
    }
    
    @Autowired
    public void setInvestorDao(IInvestorDao investorDao) {
        this.investorDao = investorDao;
    }
    
    // /mvc/stock_controller/add/stock?stockCode=2303&stockName=聯電
    @RequestMapping(value = "/add/stock")
    @ResponseBody
    public String addStock(Stock stock) {
        stockDao.create(stock);
        return "Add Stock ok";
    }
    
    // /mvc/stock_controller/add/fund?name=A&desc=科技&value=10
    @RequestMapping(value = "/add/fund")
    @ResponseBody
    public String addFund(Fund fund, @RequestParam int value, @RequestParam String traderName) {
        // 建立基金淨值
        FundNet fundNet = new FundNet();
        fundNet.setValue(value);
        fundNet.setFund(fund);
        fund.setFundNet(fundNet);
        
        Trader trader = new Trader(traderName);
        fund.getTraders().add(trader);
        
        fundDao.create(fund);
        
        
        return "Add Fund ok";
    }
    
    // /mvc/stock_controller/add/trader?name=John&fundId=1
    @RequestMapping(value = "/add/trader")
    @ResponseBody
    public String addTrader(Trader trader, @RequestParam int fundId) {
        Fund fund = fundDao.get(Fund.class, fundId);
        if(fund == null) {
            return "addTrader Error, No fund";
        }
        trader.setFund(fund);
        traderDao.create(trader);
        
        return "Add Trader ok";
    }
    
    // /mvc/stock_controller/add/investor?name=Mary&units=10000&&fundId=1
    @RequestMapping(value = "/add/investor")
    @ResponseBody
    public String addInvestor(Investor investor, @RequestParam int fundId) {
        Fund fund = fundDao.get(Fund.class, fundId);
        if(fund == null) {
            return "addInvestor Error, No fund";
        }
        investor.setNetValue(fund.getFundNet().getValue());
        investor.setFund(fund);
        investorDao.create(investor);
        
        return "Add Trader ok";
    }
    
    //-----------------------------------------------------------------------------------------------------
    
    // /mvc/stock_controller/query/stock
    @RequestMapping(value = "/query/stock", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryStock() {
        List<Stock> list = stockDao.queryAll(Stock.class);
        return gson.toJson(list);
    }
    
    // /mvc/stock_controller/query/fund
    @RequestMapping(value = "/query/fund", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryFund() {
        List<Fund> list = fundDao.queryAll(Fund.class);
        return gson.toJson(list);
    }
    
    @RequestMapping(value = "/query/trader", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryTrader() {
        List<Trader> list = traderDao.queryAll(Trader.class);
        return gson.toJson(list);
    }
    
    @RequestMapping(value = "/query/investor", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryInvestor() {
        List<Investor> list = investorDao.queryAll(Investor.class);
        return gson.toJson(list);
    }
    
    @RequestMapping(value = "/query/investor/{name}", produces="application/json;charset=utf-8")
    @ResponseBody
    public String queryInvestorByName(@PathVariable(value = "name") String name) {
        List<Investor> list = investorDao.queryByName(name);
        return gson.toJson(list);
    }
    
    //-----------------------------------------------------------------------------------------------------
        
    // /mvc/stock_controller/update/stock/6?stockCode=3008&stockName=大立光
    @RequestMapping(value = "/update/stock/{id}")
    @ResponseBody
    public String updateStock(@PathVariable(value = "id") Integer id, @RequestParam String stockCode, @RequestParam String stockName) {
        Stock stock = stockDao.get(Stock.class, id);
        if(stock == null) {
            return "Update Stock Error ! (Not found)";
        }
        stock.setStockCode(stockCode);
        stock.setStockName(stockName);
        stockDao.update(stock);
        return "Update Stock ok";
    }
    
    @RequestMapping(value = "/update/fund/{id}")
    @ResponseBody
    public String updateFund(@PathVariable(value = "id") Integer id, Fund ufund, @RequestParam int value, @RequestParam String traderName) {
        Fund fund = fundDao.get(Fund.class, id);
        if(fund == null) {
            return "Update Fund Error ! (Not found)";
        }
        fund.setName(ufund.getName());
        fund.setDesc(ufund.getDesc());
        fund.getFundNet().setValue(value);
        fund.getTraders().iterator().next().setName(traderName);
        fundDao.update(fund);
        return "Update Fund ok ! ";
    }
    
    // /mvc/stock_controller/update/fund_stock/1?stockIds=6&stockIds=8
    @RequestMapping(value = "/update/fund_stock/{id}")
    @ResponseBody
    public String updateFundStock(@PathVariable(value = "id") Integer id, @RequestParam int [] stockIds) {
        // 清除關聯檔
        fundDao.deleteStockFundRef(id);
        
        Fund fund = fundDao.get(Fund.class, id);
        if(fund == null) {
            return "Update FundStock Error ! (Not found)";
        }
        
        fundDao.appendStockFundRef(id, stockIds);
        
        return "Update FundStock ok";
    }
    
    //-----------------------------------------------------------------------------------------------------
    
    // /mvc/stock_controller/delete/stock/7
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
    
    //-----------------------------------------------------------------------------------------------------
    
    // /mvc/stock_controller/get/stock/6
    @RequestMapping(value = "/get/stock/{id}", produces="application/json;charset=utf-8")
    @ResponseBody
    public String getStock(@PathVariable(value = "id") Integer id) {
        Stock stock = stockDao.get(Stock.class, id);
        if(stock == null) {
            return "Get Stock Error ! (Not found)";
        }
        Gson gson = new GsonBuilder()     
            .excludeFieldsWithoutExposeAnnotation()
            .create();
        return gson.toJson(stock);
    }
    
    @RequestMapping(value = "/get/fund/{id}", produces="application/json;charset=utf-8")
    @ResponseBody
    public String getFund(@PathVariable(value = "id") Integer id) {
        Fund fund = fundDao.get(Fund.class, id);
        if(fund == null) {
            return "Get Fund Error ! (Not found)";
        }
        Gson gson = new GsonBuilder()     
            .excludeFieldsWithoutExposeAnnotation()
            .create();
        return gson.toJson(fund);
    }
    
}