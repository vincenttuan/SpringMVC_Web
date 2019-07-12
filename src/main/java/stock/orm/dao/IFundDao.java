package stock.orm.dao;

import stock.orm.model.Fund;

public interface IFundDao extends IBaseDao {

    public Fund find(String name);

}