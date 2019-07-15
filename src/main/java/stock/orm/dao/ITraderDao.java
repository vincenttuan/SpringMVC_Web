package stock.orm.dao;

import stock.orm.model.Trader;

public interface ITraderDao extends IBaseDao {
    Trader findByName(String name);
}
