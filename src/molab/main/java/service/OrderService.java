package molab.main.java.service;

import java.util.List;

import molab.main.java.dao.OrderDao;
import molab.main.java.entity.T_Order;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public T_Order load(long id) {
		return orderDao.load(id);
	}
	
	public List<T_Order> loadAll(int developerId) {
		return orderDao.loadAll(developerId);
	}
	
	public long save(T_Order order) throws Exception {
		try {
			return orderDao.save(order);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public int saveOrUpdate(T_Order order) throws Exception {
		try {
			orderDao.saveOrUpdate(order);
			return Status.SUCCESS;
		} catch(Exception e) {
			throw e;
		}
	}

}
