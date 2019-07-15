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
    
    // 清除關聯檔
    @Override
    @Transactional
    public void deleteStockFundRef(int fundId) {
        String sql = "Delete from STOCK_FUND Where fund_id = " + fundId;
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
        q.executeUpdate();
    }
    
    // 設定關聯檔
    @Override
    @Transactional
    public void appendStockFundRef(int fundId, int... stockIds) {
        String sql = "Insert Into STOCK_FUND(fund_id, stock_id) Values(%d, %d)";
        for(int stockId : stockIds) {
            String add_sql = String.format(sql, fundId, stockId);
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(add_sql);
            q.executeUpdate();
        }
    }
    
}
