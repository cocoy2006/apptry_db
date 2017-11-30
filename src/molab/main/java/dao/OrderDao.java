package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.T_Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends BaseDao<T_Order> {
	
	private static String SELECT_ORDER_WITH_DEVELOPER_ID = "FROM T_Order WHERE developer_id=?";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public T_Order load(long id) {
		return hibernateTemplate.load(T_Order.class, id);
	}
	
	public List<T_Order> loadAll(int developerId) {
		return hibernateTemplate.find(SELECT_ORDER_WITH_DEVELOPER_ID, developerId);
	}
	
	public Long save(T_Order order) {
		return (Long) hibernateTemplate.save(order);
	}
	
	public void saveOrUpdate(T_Order order) {
		hibernateTemplate.saveOrUpdate(order);
	}

}
