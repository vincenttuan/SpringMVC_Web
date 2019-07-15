package stock.orm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.IFundDao;
import stock.orm.dao.IStockDao;
import stock.orm.dao.ITraderDao;
import stock.orm.model.Fund;
import stock.orm.model.FundNet;
import stock.orm.model.Stock;
import stock.orm.model.Trader;


public class Fund_CRUD {
    
    private static ApplicationContext context;
    private static IFundDao fundDao;
    private static IStockDao stockDao;
    private static ITraderDao traderDao;
    
    static {
        context = new ClassPathXmlApplicationContext("applicationContext_stock.xml");
        stockDao = (IStockDao)context.getBean("stockDao");
        traderDao = (ITraderDao)context.getBean("traderDao");
        fundDao = (IFundDao)context.getBean("fundDao");
    }
    
    public static void main(String[] args) {
        
//        create("A", "高科技");
//        create("B", "高收益");
//        create("C", "高股息");
        
        //addStock("A", "2330", "3008", "2303");
        //addTrader("A", "John", "Mary");
        find("A");
    }
    
    public static void create(String name, String desc) {
        
        // 建立新基金
        Fund fund = new Fund(name, desc);
        
        // 建立基金淨值
        FundNet fundNet = new FundNet();
        fundNet.setValue(10);
        fundNet.setFund(fund);
        fund.setFundNet(fundNet);
        
        fundDao.create(fund);
    }
    
    public static void addStock(String fundName, String... stockCodes) {
        // 查找新基金
        Fund fund = fundDao.find(fundName);
        
        for(String stockCode : stockCodes){
            // 查找 stock
            Stock stock = stockDao.findByCode(stockCode);
            // 加入股票
            fund.getStocks().add(stock);
        }
        
        fundDao.update(fund);
    }
    
    public static void addTrader(String fundName, String... traderNames) {
        // 查找新基金
        Fund fund = fundDao.find(fundName);
        
        for(String traderName : traderNames) {
            // 查找 trader
            Trader trader = traderDao.findByName(traderName);
            // 加入 trader
            fund.getTraders().add(trader);
        }
        
        fundDao.update(fund);
    }
    
    public static void find(String fundName) {
        // 查找新基金
        Fund fund = fundDao.find(fundName);
        System.out.println("基金名稱: " + fund.getName() + " " + fund.getDesc());
        System.out.print("成分股:");
        fund.getStocks().stream().forEach(e -> System.out.print(" " + e.getStockName()));
        System.out.print("\n操盤人:");
        fund.getTraders().stream().forEach(e -> System.out.print(" " + e.getName()));
        System.out.print("\n投資人:");
        fund.getInvestors().stream().forEach(e -> System.out.print(" " + e.getName()));
        
    }
}
