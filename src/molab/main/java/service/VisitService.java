package molab.main.java.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import molab.main.java.component.VisitListComponent;
import molab.main.java.dao.ApptryDao;
import molab.main.java.dao.VisitDao;
import molab.main.java.entity.T_Application;
import molab.main.java.entity.T_Visit;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

	@Autowired
	private VisitDao visitDao;
	
	@Autowired
	private ApptryDao apptryDao;
	
	public int save(T_Visit visit) {
		try {
			return visitDao.save(visit);
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public List<VisitListComponent> loadList(int developerId) {
		try {
			// first load applications from t_dispatcher according to developer_id
			List<T_Application> applications = apptryDao.loadApplications(developerId);
			if(applications != null) {
				List<VisitListComponent> list = new ArrayList<VisitListComponent>();
				for(T_Application application : applications) {
					// get totalClicks and monthClicks from t_visit according to application_id
					int applicationId = application.getId();
					long totalClicks = visitDao.loadClicks(applicationId, 0);
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					long firstTime = cal.getTimeInMillis(); // first time in this month
					long monthClicks = visitDao.loadClicks(applicationId, firstTime);
					// set visit list component
					VisitListComponent vlc = new VisitListComponent();
					vlc.setApplication(application);
					vlc.setTotalClicks(totalClicks);
					vlc.setMonthClicks(monthClicks);
					list.add(vlc);
				}
				return list;
			}
			return null;
		} catch(Exception e) {
			// log
			return null;
		}
	}
	
	public List<T_Visit> loadDetail(int applicationId) {
		try {
			return visitDao.loadDetail(applicationId);
		} catch(Exception e) {
			// log
			return null;
		}
	}

}
