package molab.main.java.dao;

import java.sql.SQLException;
import java.util.List;

import molab.main.java.entity.T_Dispatcher;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DispatcherDao extends BaseDao<T_Dispatcher> {
	
	private static final String SELECT_MINIMUM_MONTH_CLICKS =
		"SELECT e.id, s.ipAddress, s.port, e.serialNumber FROM T_Emulator e, T_Server s ORDER BY monthClicks ASC";
	private static final String SELECT_EMULATORS_WITH_APPLICATION_ID = 
		"SELECT e.id, s.ipAddress, s.port, e.serialNumber " +
		"FROM T_Emulator e, T_Server s, T_Dispatcher dis, T_Deployment dep " +
		"WHERE dis.application_id=? AND dep.dispatcher_id=dis.id " +
		"AND e.id=dep.emulator_id AND s.id=e.server_id";
	private static final String SELECT_SELF_WITH_APPLICATION_ID = 
		"FROM T_Dispatcher where application_id=?";
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public T_Dispatcher load(int id) {
		return hibernateTemplate.load(T_Dispatcher.class, id);
	}
	
	public T_Dispatcher loadSelfWithApplicationId(int applicationId) {
		return (T_Dispatcher) hibernateTemplate.find(SELECT_SELF_WITH_APPLICATION_ID, applicationId).get(0);
	}
	
	public Integer save(T_Dispatcher dispatcher) {
		return (Integer) hibernateTemplate.save(dispatcher);
	}
	
	public void saveOrUpdate(T_Dispatcher dispatcher) {
		hibernateTemplate.saveOrUpdate(dispatcher);
	}
	
	public void delete(T_Dispatcher dispatcher) {
		hibernateTemplate.delete(dispatcher);
	}
	
	public List loadEmulators(final int num) {
		
		return hibernateTemplate.executeFind(new HibernateCallback() {

			public List doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				Query query = arg0.createQuery(SELECT_MINIMUM_MONTH_CLICKS);
				query.setMaxResults(num);
				return query.list();
			}

		});
	}
	
	public List loadEmulatorsWithApplicationId(int applicationId) {
		return hibernateTemplate.find(SELECT_EMULATORS_WITH_APPLICATION_ID, applicationId);
	}

}
