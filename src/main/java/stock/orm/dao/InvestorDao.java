package stock.orm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.query.Query;
import stock.orm.model.Investor;

public class InvestorDao extends BaseDao implements IInvestorDao{

    @Override
    @Transactional
    public List<Investor> queryByName(String name) {
        String sql = "from Investor where name =:name";
        Query query = getSessionFactory().getCurrentSession().createQuery(sql, Investor.class);
        query.setParameter("name", name);
        return query.list();
    }
    
}
