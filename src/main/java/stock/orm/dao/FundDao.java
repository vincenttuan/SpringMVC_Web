package stock.orm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.query.Query;
import stock.orm.model.Fund;

public class FundDao extends BaseDao implements IFundDao {

    @Override
    @Transactional
    public Fund find(String name) {
        String sql = "from Fund where name = :name";
        Query query = getSessionFactory().getCurrentSession().createQuery(sql, Fund.class);
        query.setParameter("name", name).setMaxResults(1);
        List<Fund> list = query.list();
        return list.size() > 0 ? list.get(0) : null;
    }
    
}
