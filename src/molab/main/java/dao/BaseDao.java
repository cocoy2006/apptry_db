package molab.main.java.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDao<T> {

	private Class<T> entityClass;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(entityClass, id);
	}
	
	public T get(Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}
	
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	public Serializable save(T entity) {
		return getHibernateTemplate().save(entity);
	}
	
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}
	
	public T merge(T entity) {
		return getHibernateTemplate().merge(entity);
	}
	
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... params) {
		return getHibernateTemplate().find(hql, params);
	}
	
	public void initialize(Object entity) {
		getHibernateTemplate().initialize(entity);
	}
}
