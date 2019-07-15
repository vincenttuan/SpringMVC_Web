package stock.orm.dao;

import stock.orm.model.Stock;

public interface IStockDao extends IBaseDao {
    Stock findByCode(String stockCode);
}
