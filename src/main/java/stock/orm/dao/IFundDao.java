package stock.orm.dao;

import stock.orm.model.Fund;

public interface IFundDao extends IBaseDao {

    public Fund find(String name);
    
    // 清除關聯檔
    public void deleteStockFundRef(int fundId);
    
    // 設定關聯檔
    public void appendStockFundRef(int fundId, int... stockIds);
}