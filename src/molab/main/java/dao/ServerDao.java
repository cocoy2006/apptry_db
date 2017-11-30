package molab.main.java.dao;

import java.util.List;

import molab.main.java.entity.T_Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public class ServerDao extends BaseDao<T_Server> {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private static final String LOAD_SERVER = "FROM T_Server WHERE ipAddress=?";
	
	
	public T_Server load(int id) {
		return hibernateTemplate.load(T_Server.class, id);
	}
	
	public List<T_Server> loadAll() {
		return hibernateTemplate.loadAll(T_Server.class);
	}
	
	public T_Server load(String ipAddress) {
		List list = hibernateTemplate.find(LOAD_SERVER, ipAddress);
		if(list != null && list.size() != 0) {
			return (T_Server) list.get(0);
		}
		return null;
	}
	
	public Integer save(T_Server server) {
		return (Integer) hibernateTemplate.save(server);
	}
	
	public void delete(T_Server server) {
		hibernateTemplate.delete(server);
	}

}
