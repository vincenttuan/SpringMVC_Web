package stock.orm.dao;

import java.util.List;

public interface IBaseDao {

    // 新增
    public void create(Object obj);
    
    // 修改
    public void update(Object obj);
    
    // 刪除
    public void delete(Object obj);

    // 查詢單筆
    public <T extends Object> T get(Class<T> cla, Integer id);

    // 查詢多筆
    public <T extends Object> List<T> queryAll(Class<T> cla);
    
    
}
