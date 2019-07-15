package stock.orm.model;

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

public class Fund {

    @Expose
    private Integer fundId;
    @Expose
    private String name;
    @Expose
    private String desc;
    @Expose
    private Set<Stock> stocks = new HashSet<>();
    @Expose
    private FundNet fundNet;
    @Expose
    private Set<Trader> traders = new HashSet();
    
    private Set<Investor> investors = new HashSet();
    
    public Fund() {
    }
    
    public Fund(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public FundNet getFundNet() {
        return fundNet;
    }

    public void setFundNet(FundNet fundNet) {
        this.fundNet = fundNet;
    }

    public Set<Trader> getTraders() {
        return traders;
    }

    public void setTraders(Set<Trader> traders) {
        this.traders = traders;
    }

    public Set<Investor> getInvestors() {
        return investors;
    }

    public void setInvestors(Set<Investor> investors) {
        this.investors = investors;
    }

    @Override
    public String toString() {
        return "Fund{" + "fundId=" + fundId + ", name=" + name + ", desc=" + desc + ", stocks=" + stocks + ", fundNet=" + fundNet + ", traders=" + traders + ", investors=" + investors + '}';
    }

    
    
    

}
