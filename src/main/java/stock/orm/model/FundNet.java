package stock.orm.model;

import com.google.gson.annotations.Expose;

public class FundNet {
    @Expose
    private Integer netId;
    @Expose
    private Integer value;
    private Fund fund;

    public FundNet() {
    }

    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    @Override
    public String toString() {
        return "FundNet{" + "netId=" + netId + ", value=" + value + ", fund=" + fund + '}';
    }

    
    
    
}
