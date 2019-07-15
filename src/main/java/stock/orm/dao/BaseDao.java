package stock.orm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;

public abstract class BaseDao<T> implements IBaseDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    @Override
    @Transactional
    public void create(Object obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    @Transactional
    public <T> T get(Class<T> cla, Integer id) {
        return sessionFactory.getCurrentSession().get(cla, id);
    }

    @Override
    @Transactional
    public <T> List<T> queryAll(Class<T> cla) {
        List list = sessionFactory.getCurrentSession().createQuery("from " + cla.getSimpleName()).list();
        return list;
    }

    
    @Override
    @Transactional
    public void update(Object obj) {
        //sessionFactory.getCurrentSession().refresh(obj);
        sessionFactory.getCurrentSession().saveOrUpdate(obj);
    }

    @Override
    @Transactional
    public void delete(Object obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }
    
    
}
