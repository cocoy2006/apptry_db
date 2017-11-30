package molab.main.java.service;

import java.util.List;

import molab.main.java.dao.DeploymentDao;
import molab.main.java.entity.T_Deployment;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeploymentService {

	@Autowired
	private DeploymentDao deploymentDao;
	
	public T_Deployment load(int deploymentId) {
		return deploymentDao.load(deploymentId);
	}
	
	public List<T_Deployment> loadAll() {
		try {
			return deploymentDao.loadAll();
		} catch(Exception e) {
			// log
			return null;
		}
	}
	
	public List<T_Deployment> loadAll(int applicationId) {
		return deploymentDao.loadAll(applicationId);
	}
	
	public List<T_Deployment> loadAllEmulators(int emulatorId) {
		try {
			return deploymentDao.loadAllEmulators(emulatorId);
		} catch(Exception e) {
			// log
			return null;
		}
	}
	
	public int save(int emulatorId, int applicationId) {
		try {
			T_Deployment deployment = new T_Deployment();
			deployment.setEmulator_id(emulatorId);
			deployment.setApplication_id(applicationId);
			return deploymentDao.save(deployment);
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int save(T_Deployment deployment) {
		try {
			return deploymentDao.save(deployment);
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int saveOrUpdate(int id, int emulatorId, int applicationId) {
		try {
			T_Deployment deployment = deploymentDao.load(id);
			if(deployment != null) {
				deployment.setEmulator_id(emulatorId);
				deployment.setApplication_id(applicationId);
				deploymentDao.saveOrUpdate(deployment);
				return Status.SUCCESS; 
			} else {
				throw new Exception("deployment with specified id does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int saveOrUpdate(T_Deployment deployment) {
		try {
			deploymentDao.saveOrUpdate(deployment);
			return Status.SUCCESS; 
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}

	public int delete(int id) {
		try {
			T_Deployment deployment = deploymentDao.load(id);
			if(deployment != null) {
				deploymentDao.delete(deployment);
				return Status.SUCCESS;
			} else {
				throw new Exception("deployment with specified id does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
}
