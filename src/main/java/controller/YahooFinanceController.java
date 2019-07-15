package controller;

import com.google.gson.Gson;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Controller
@RequestMapping("/yahoo_controller")
public class YahooFinanceController {
    
    @RequestMapping(value = "/get/stock/{symbol}", produces="application/json;charset=utf-8")
    @ResponseBody
    public String getData(@PathVariable(value = "symbol") String symbol) throws Exception{
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.MONTH, -1); 

        Stock stock = YahooFinance.get(symbol + ".TW");
        List<HistoricalQuote> stockHistQuotes = stock.getHistory(from, to, Interval.DAILY);
        
        return new Gson().toJson(stockHistQuotes);
    }
    
}
