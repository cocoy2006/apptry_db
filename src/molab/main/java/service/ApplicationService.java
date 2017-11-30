package molab.main.java.service;

import java.sql.Blob;
import java.util.List;

import molab.main.java.dao.ApplicationDao;
import molab.main.java.dao.DeploymentDao;
import molab.main.java.dao.DispatcherDao;
import molab.main.java.entity.T_Application;
import molab.main.java.entity.T_Deployment;
import molab.main.java.entity.T_Dispatcher;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private DispatcherDao dispatcherDao;
	
	@Autowired
	private DeploymentDao deploymentDao;
	
	public T_Application load(int applicationId) {
		return applicationDao.load(applicationId);
	}
	
	public T_Application loadMd5(String md5) {
		List<T_Application> list = applicationDao.loadMd5(md5);
		if(list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	} 
	
	public List loadAll() {
		try {
			return applicationDao.loadAll();
		} catch(Exception e) {
			// log
			return null;
		}
	}
	
	public int saveOrUpdate(String name, String packageName, String startActivity) {
		return 0;
	}
	
	public int save(String name, String packageName, String startActivity) {
		try {
			T_Application application = new T_Application();
			application.setName(name);
			application.setPackageName(packageName);
			application.setStartActivity(startActivity);
			return applicationDao.save(application);
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int save(String md5, String name, 
			String aliasName, long size,
			String packageName, String version,
			String os, String startActivity, Blob icon) {
		try {
			T_Application application = new T_Application();
			application.setMd5(md5);
			application.setName(name);
			application.setAliasName(aliasName);
			application.setSize(size);
			application.setPackageName(packageName);
			application.setVersion(version);
			application.setOs(os);
			application.setStartActivity(startActivity);
			application.setIcon(icon);
			return applicationDao.save(application);
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int saveOrUpdate(int id, String name, String packageName, String startActivity) {
		try {
			T_Application application = applicationDao.load(id);
			if(application != null) {
				application.setName(name);
				application.setPackageName(packageName);
				application.setStartActivity(startActivity);
				applicationDao.saveOrUpdate(application);
				return Status.SUCCESS; 
			} else {
				throw new Exception("application with specified id does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}

	public int delete(int id) {
		try {
			T_Application application = applicationDao.load(id);
			if(application != null) {
				applicationDao.delete(application);
				return Status.SUCCESS;
			} else {
				throw new Exception("application with specified id does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int check(int developerId, String packageName) {
		try {
			List<T_Application> applications = applicationDao.loadWithDeveloperId(developerId);
			if(applications != null && applications.size() > 0) {
				for(T_Application application : applications) {
					if(packageName.equalsIgnoreCase(application.getPackageName())) {
						return application.getId();
					}
				}
			}
			return Status.FALSE;
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int remove(int id) {
		try {
			// remove information from t_application first
			T_Application application = applicationDao.load(id);
			if(application != null) {
				applicationDao.delete(application);
				// remove info from t_dispatcher
				T_Dispatcher dispatcher = dispatcherDao.loadSelfWithApplicationId(application.getId());
				if(dispatcher != null) {
					dispatcherDao.delete(dispatcher);
					// remove info from t_deployment
					List<T_Deployment> deployments = deploymentDao.loadAllWithDispatcherId(dispatcher.getId());
					if(deployments != null) {
						for(T_Deployment deployment : deployments) {
							deploymentDao.delete(deployment);
						}
					}
				}				
			}
			return Status.SUCCESS;
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
}
