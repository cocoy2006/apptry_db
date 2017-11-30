package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.T_Deployment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeploymentDao extends BaseDao<T_Deployment> {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static String SELECT_DEPLOYMENT_WITH_APPLICATION_ID = 
		"SELECT D FROM T_Deployment D, T_Emulator E WHERE D.application_id=? " +
		"AND D.emulator_id=E.id AND E.state=1 ORDER BY E.monthClicks ASC";
	private static String SELECT_DEPLOYMENT_WITH_DISPATCHER_ID = 
		"FROM T_Deployment WHERE dispatcher_id=?";
	private static String SELECT_DEPLOYMENT_WITH_EMULATOR_ID = "FROM T_Deployment WHERE emulator_id=?";
	
	@SuppressWarnings("unchecked")
	public List<T_Deployment> loadAll(int applicationId) {
		return hibernateTemplate.find(SELECT_DEPLOYMENT_WITH_APPLICATION_ID, applicationId);
	}
	
	@SuppressWarnings("unchecked")
	public List<T_Deployment> loadAllWithDispatcherId(int dispatcherId) {
		return hibernateTemplate.find(SELECT_DEPLOYMENT_WITH_DISPATCHER_ID, dispatcherId);
	}
	
	@SuppressWarnings("unchecked")
	public List<T_Deployment> loadAllEmulators(int emulatorId) {
		return hibernateTemplate.find(SELECT_DEPLOYMENT_WITH_EMULATOR_ID, emulatorId);
	}
	
	public T_Deployment load(int id) {
		return hibernateTemplate.load(T_Deployment.class, id);
	}
	
	public Integer save(T_Deployment deployment) {
		return (Integer) hibernateTemplate.save(deployment);
	}
	
	public void delete(T_Deployment deployment) {
		hibernateTemplate.delete(deployment);
	}

}
