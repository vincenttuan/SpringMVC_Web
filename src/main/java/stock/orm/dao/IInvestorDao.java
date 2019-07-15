package stock.orm.dao;

import java.util.List;
import stock.orm.model.Investor;

public interface IInvestorDao extends IBaseDao {
    List<Investor> queryByName(String name);
}
