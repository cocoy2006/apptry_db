package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.T_Dispatcher;
import molab.main.java.entity.T_Visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VisitDao extends BaseDao<T_Dispatcher> {
	
	private static final String SELECT_CLICKS = 
		"SELECT COUNT(*) FROM T_Visit WHERE application_id=? AND loginTime>=?";
	private static final String SELECT_DETAIL =
		"FROM T_Visit WHERE application_id=?";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public Integer save(T_Visit visit) {
		return (Integer) hibernateTemplate.save(visit);
	}
	
	public Long loadClicks(int applicationId, long loginTime) {
		List<?> list = hibernateTemplate.find(SELECT_CLICKS, applicationId, loginTime);
		if(list != null && list.size() > 0) {
			return (Long) list.get(0);
		}
		return 0L;
	}
	
	public List<T_Visit> loadDetail(int applicationId) {
		return hibernateTemplate.find(SELECT_DETAIL, applicationId);
	}
	
}
