package molab.main.java.dao;

import molab.main.java.entity.T_Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends BaseDao<T_Product> {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public T_Product load(int id) {
		return hibernateTemplate.load(T_Product.class, id);
	}
		
	public Integer save(T_Product product) {
		return (Integer) hibernateTemplate.save(product);
	}
	
	public void saveOrUpdate(T_Product product) {
		hibernateTemplate.saveOrUpdate(product);
	}
	
	public void delete(T_Product product) {
		hibernateTemplate.delete(product);
	}
	
}
