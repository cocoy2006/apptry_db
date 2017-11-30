package molab.main.java.service;

import java.util.List;

import molab.main.java.dao.ServerDao;
import molab.main.java.entity.T_Server;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

	@Autowired
	private ServerDao serverDao;
	
	public T_Server load(int id) {
		return serverDao.load(id);
	}
	
	public List<T_Server> loadAll() {
		try {
			return serverDao.loadAll();
		} catch(Exception e) {
			// log
			return null;
		}
	}
	
	public int save(String ipAddress, int port) {
		try {
			T_Server server = new T_Server();
			server.setIpAddress(ipAddress);
			server.setPort(port);
			return serverDao.save(server);
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int saveOrUpdate(int id, String ipAddress, int port) {
		try {
			T_Server server = serverDao.load(id);
			if(server != null) {
				server.setIpAddress(ipAddress);
				server.setPort(port);
				serverDao.saveOrUpdate(server);
				return Status.SUCCESS;
			} else {
				throw new Exception("server with specified id does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int saveOrUpdate(String oldIpAddress, String newIpAddress) {
		try {
			T_Server server = serverDao.load(oldIpAddress);
			if(server != null) {
				server.setIpAddress(newIpAddress);
				serverDao.saveOrUpdate(server);
				return Status.SUCCESS;
			} else {
				throw new Exception("server with specified ip address does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int delete(int id) {
		try {
			T_Server server = serverDao.load(id);
			if(server != null) {
				serverDao.delete(server);
				return Status.SUCCESS;
			} else {
				throw new Exception("server with specified id does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int delete(String ipAddress) {
		try {
			T_Server server = serverDao.load(ipAddress);
			if(server != null) {
				serverDao.delete(server);
				return Status.SUCCESS;
			} else {
				throw new Exception("server with specified ip address does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
}
