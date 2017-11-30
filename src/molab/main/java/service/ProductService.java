package molab.main.java.service;

import molab.main.java.dao.ProductDao;
import molab.main.java.entity.T_Product;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public T_Product load(int id) {
		return productDao.load(id);
	}
	
	public int update(T_Product user) {
		try {
			productDao.saveOrUpdate(user);
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}
	
	public int delete(String[] ids) {
		try {
			T_Product user;
			for(String id : ids) {
				int i = Integer.parseInt(id);
				user = productDao.load(i);
				if(user != null) {
					productDao.delete(user);
				}
			}
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}
}