package stock.orm.test;


import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.ITraderDao;
import stock.orm.model.Trader;

public class Trader_CRUD {
    static ApplicationContext context;
    static ITraderDao traderDao;
    static {
        context = new ClassPathXmlApplicationContext("applicationContext_stock.xml");
        traderDao = (ITraderDao)context.getBean("traderDao");
    }
    public static void main(String[] args) {
        //add();
        queryAll().stream()
                .filter(e -> e.getName().length() >= 4)
                .forEach(e -> System.out.println(e.getName()));
    }
    
    public static void add() {
        Trader t1 = new Trader("John");
        Trader t2 = new Trader("Mary");
        Trader t3 = new Trader("Tom");
        traderDao.create(t1);
        traderDao.create(t2);
        traderDao.create(t3);
    }
    
    public static List<Trader> queryAll() {
        return traderDao.queryAll(Trader.class);
    }
    
}
