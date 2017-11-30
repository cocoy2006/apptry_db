package molab.main.java.dao;

import java.sql.SQLException;
import java.util.List;

import molab.main.java.entity.T_Emulator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmulatorDao extends BaseDao<T_Emulator> {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static String SELECT_EMULATOR_WITH_SERVER_ID = 
		"FROM T_Emulator WHERE server_id=?";
	private static final String SELECT_DISTINCT_OS = 
		"SELECT DISTINCT os FROM T_Emulator ORDER BY os";
	private static final String SELECT_DISTINCT_DPI = 
		"SELECT DISTINCT dpi FROM T_Emulator ORDER BY dpi";
	private static final String SELECT_MINIMUM_MONTH_CLICKS =
		"FROM T_Emulator ORDER BY monthClicks ASC";
	
	public T_Emulator load(int id) {
		return hibernateTemplate.load(T_Emulator.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T_Emulator> loadAll(int serverId) {
		return hibernateTemplate.find(SELECT_EMULATOR_WITH_SERVER_ID, serverId);
	}
	
	@SuppressWarnings("unchecked")
	public List<T_Emulator> loadMinimumMonthClicks(final int num) {
//		return hibernateTemplate.find(SELECT_MINIMUM_MONTH_CLICKS, num);
		return hibernateTemplate.executeFind(new HibernateCallback() {

			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				Query query = arg0.createQuery(SELECT_MINIMUM_MONTH_CLICKS);
				query.setMaxResults(num);
				return query.list();
			}
			
		});
	}
	
	public List<String> loadAllOs() {
		return hibernateTemplate.find(SELECT_DISTINCT_OS);
	}
	
	public List<String> loadAllDpi() {
		return hibernateTemplate.find(SELECT_DISTINCT_DPI);
	}
	
	public List loadEmulatorsDynatree(DetachedCriteria dc) {
		return hibernateTemplate.findByCriteria(dc);
	}
	
	public Integer save(T_Emulator emulator) {
		return (Integer) hibernateTemplate.save(emulator);
	}
	
	public void saveOrUpdate(T_Emulator emulator) {
		hibernateTemplate.saveOrUpdate(emulator);
	}

}
