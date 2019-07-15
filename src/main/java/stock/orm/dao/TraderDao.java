package stock.orm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.query.Query;
import stock.orm.model.Stock;
import stock.orm.model.Trader;

public class TraderDao extends BaseDao<Trader> implements ITraderDao{

    @Override
    @Transactional
    public Trader findByName(String name) {
        String sql = "from Trader where name =:name";
        Query query = getSessionFactory().getCurrentSession().createQuery(sql, Trader.class);
        query.setParameter("name", name).setMaxResults(1);
        List<Trader> list = query.list();
        return list.size() > 0 ? list.get(0) : null;
    }
    
}
