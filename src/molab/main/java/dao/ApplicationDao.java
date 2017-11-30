package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.T_Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationDao extends BaseDao<T_Application> {

	private static String SELECT_APPLICATION_WITH_MD5 = 
		"FROM T_Application WHERE md5=?";
	private static String SELECT_APPLICATION_WITH_DEVELOPER_ID = 
		"FROM T_Application WHERE id IN (SELECT application_id FROM T_Dispatcher WHERE developer_id=?)";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	public T_Application load(int id) {
		return hibernateTemplate.load(T_Application.class, id);
	}
	
	public List<T_Application> loadMd5(String md5) {
		return hibernateTemplate.find(SELECT_APPLICATION_WITH_MD5, md5);
	}
	
	public List<T_Application> loadWithDeveloperId(int developerId) {
		return hibernateTemplate.find(SELECT_APPLICATION_WITH_DEVELOPER_ID, developerId);
	}
	
	public void saveOrUpdate(T_Application application) {
		hibernateTemplate.saveOrUpdate(application);
	}
	
	public Integer save(T_Application application) {
		return (Integer) hibernateTemplate.save(application);
	}
	
	public void delete(T_Application application) {
		hibernateTemplate.delete(application);
	}
	
}
