package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.T_Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApptryDao {
	
	private static final String SELECT_APPLICATIONS_WITH_DEVELOPERID =
		"FROM T_Application WHERE id IN " +
		"(SELECT application_id FROM T_Dispatcher WHERE developer_id=?)";

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<T_Application> loadApplications(int developerId) {
		return hibernateTemplate.find(SELECT_APPLICATIONS_WITH_DEVELOPERID, developerId);
	}

}
