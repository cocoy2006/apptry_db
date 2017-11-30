package molab.main.java.service;

import java.util.List;

import molab.main.java.dao.ApptryDao;
import molab.main.java.entity.T_Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApptryService {

	@Autowired
	private ApptryDao apptryDao;
	
	public List<T_Application> loadApplications(int developerId) {
		try {
			return apptryDao.loadApplications(developerId);
		} catch(Exception e) {
			return null;
		}
	}
	
}
