package stock.orm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.query.Query;
import stock.orm.model.Stock;

public class StockDao extends BaseDao implements IStockDao {

    @Override
    @Transactional
    public Stock findByCode(String stockCode) {
        String sql = "from Stock where stockCode =:stockCode";
        Query query = getSessionFactory().getCurrentSession().createQuery(sql, Stock.class);
        query.setParameter("stockCode", stockCode).setMaxResults(1);
        List<Stock> list = query.list();
        return list.size() > 0 ? list.get(0) : null;
    }
    
}
