package stock.orm.test;


import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.orm.dao.IFundDao;
import stock.orm.dao.IInvestorDao;
import stock.orm.model.Fund;
import stock.orm.model.Investor;
import stock.orm.model.Trader;

public class Investor_CRUD {
    private static ApplicationContext context;
    private static IInvestorDao investorDao;
    private static IFundDao fundDao;
    static {
        context = new ClassPathXmlApplicationContext("applicationContext_stock.xml");
        investorDao = (IInvestorDao)context.getBean("investorDao");
        fundDao = (IFundDao)context.getBean("fundDao");
    }
    public static void main(String[] args) {
        add("Lucky", 10000, "A");
//        queryAll().stream()
//                .forEach(e -> {
//                    System.out.printf("%s %.0f %d %s\n", e.getName(), e.getUnits(), e.getNetValue(), e.getFund().getName());
//                });
    }
    
    public static void add(String investName, double units, String fundName) {
        Fund fund = fundDao.find(fundName);
        Investor inv = new Investor(investName);
        inv.setNetValue(fund.getFundNet().getValue());
        inv.setUnits(units);
        inv.setFund(fund);
        investorDao.create(inv);
    }
    
    
    public static List<Investor> queryAll() {
        return investorDao.queryAll(Investor.class);
    }
    
}
