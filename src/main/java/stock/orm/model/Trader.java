package stock.orm.model;

public class Trader {
    private Integer traderId;
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
