package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.T_Developer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeveloperDao extends BaseDao<T_Developer> {
	private static final String SELECT_USER 
		= "FROM T_Developer WHERE username=?";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public T_Developer load(int id) {
		return hibernateTemplate.load(T_Developer.class, id);
	}
	
	/**
	 * @param username
	 * @return T_Developer object or null if not exist */
	public T_Developer load(String username) {
		List list = hibernateTemplate.find(SELECT_USER, username);
		if(list != null && list.size() != 0) {
			return (T_Developer) list.get(0);
		}
		return null;
	}
	
	public List<T_Developer> loadAll() {
		return hibernateTemplate.loadAll(T_Developer.class);
	}
	
	public Integer save(T_Developer developer) {
		return (Integer) hibernateTemplate.save(developer);
	}
	
	public void saveOrUpdate(T_Developer developer) {
		hibernateTemplate.saveOrUpdate(developer);
	}
	
	public void delete(T_Developer developer) {
		hibernateTemplate.delete(developer);
	}
	
}
