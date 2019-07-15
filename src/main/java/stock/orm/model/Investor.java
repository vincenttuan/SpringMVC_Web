package stock.orm.model;

import com.google.gson.annotations.Expose;

public class Investor {
    @Expose
    private Integer investorId;
    @Expose
    private String name;
    @Expose
    private Double units;
    @Expose
    private Integer netValue;
    @Expose
    private Fund fund;

    public Investor() {
    }

    public Investor(String name) {
        this.name = name;
    }
    
    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Integer getNetValue() {
        return netValue;
    }

    public void setNetValue(Integer netValue) {
        this.netValue = netValue;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }
    
}
