package stock.orm.test;


import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.IStockDao;
import stock.orm.model.Stock;

public class Stock_CRUD {
    static ApplicationContext context;
    static IStockDao stockDao;
    static {
        context = new ClassPathXmlApplicationContext("applicationContext_stock.xml");
        stockDao = (IStockDao)context.getBean("stockDao");
    }
    public static void main(String[] args) {
        //add();
        update("3008", "大立光");
        queryAll().stream().forEach(e -> System.out.println(e.getStockCode() + "\t" + e.getStockName()));
    }
    
    public static void add() {
        Stock stock1 = new Stock("3008", "大力光");
        Stock stock2 = new Stock("2330", "台積電");
        Stock stock3 = new Stock("2303", "聯電");
        stockDao.create(stock1);
        stockDao.create(stock2);
        stockDao.create(stock3);
    }
    
    public static void update(String stockCode, String stockName) {
        Stock stock = stockDao.findByCode(stockCode);
        if(stock != null) {
            stock.setStockName(stockName);
            stockDao.update(stock);
        }
    }
    
    public static List<Stock> queryAll() {
        return stockDao.queryAll(Stock.class);
    }
    
}
