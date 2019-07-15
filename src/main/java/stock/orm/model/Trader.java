package stock.orm.model;

import com.google.gson.annotations.Expose;

public class Trader {
    @Expose
    private Integer traderId;
    @Expose
    private String name;
    
    private Fund fund;

    public Trader() {
    }

    public Trader(String name) {
        this.name = name;
    }

    public Integer getTraderId() {
        return traderId;
    }

    public void setTraderId(Integer traderId) {
        this.traderId = traderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    @Override
    public String toString() {
        return "Trader{" + "traderId=" + traderId + ", name=" + name + ", fund=" + fund + '}';
    }

    
    
}
