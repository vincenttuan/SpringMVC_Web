package stock.orm.model;

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

public class Stock implements java.io.Serializable {

    @Expose
    private Integer stockId;
    @Expose
    private String stockCode;
    @Expose
    private String stockName;
    
    private Set<Fund> funds = new HashSet<>();
    
    public Stock() {
        
    }

    public Stock(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }
    
    

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Set<Fund> getFunds() {
        return funds;
    }

    public void setFunds(Set<Fund> funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "Stock{" + "stockId=" + stockId + ", stockCode=" + stockCode + ", stockName=" + stockName + ", funds=" + funds + '}';
    }

    
}
