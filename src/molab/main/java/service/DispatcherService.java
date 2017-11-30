package molab.main.java.service;

import java.util.ArrayList;
import java.util.List;

import molab.main.java.component.DispatcherEmulatorComponent;
import molab.main.java.dao.DispatcherDao;
import molab.main.java.entity.T_Dispatcher;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispatcherService {

	@Autowired
	private DispatcherDao dispatcherDao;
	
	public T_Dispatcher load(int id) {
		return dispatcherDao.load(id);
	}
	
	public int save(T_Dispatcher dispatcher) {
		try {
			return dispatcherDao.save(dispatcher);
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int saveOrUpdate(T_Dispatcher dispatcher) {
		try {
			dispatcherDao.saveOrUpdate(dispatcher);
			return Status.SUCCESS;
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}

	public List<DispatcherEmulatorComponent> loadEmulators(int num) {
		try {
			List emulators = dispatcherDao.loadEmulators(num);
			List<DispatcherEmulatorComponent> list = new ArrayList<DispatcherEmulatorComponent>();
			for(Object obj : emulators) {
				Object[] object = (Object[]) obj;
				DispatcherEmulatorComponent dec = new DispatcherEmulatorComponent();
				dec.setId((Integer) object[0]);
				dec.setIpAddress((String) object[1]);
				dec.setPort((Integer) object[2]);
				dec.setSerialNumber((String) object[3]);
				list.add(dec);
			}
			return list;
		} catch(Exception e) {
			// log
			return null;
		}
	}
	
	public List<DispatcherEmulatorComponent> loadEmulatorsWithApplicationId(int applicationId) {
		try {
			List emulators = dispatcherDao.loadEmulatorsWithApplicationId(applicationId);
			List<DispatcherEmulatorComponent> list = new ArrayList<DispatcherEmulatorComponent>();
			for(Object obj : emulators) {
				Object[] object = (Object[]) obj;
				DispatcherEmulatorComponent dec = new DispatcherEmulatorComponent();
				dec.setId((Integer) object[0]);
				dec.setIpAddress((String) object[1]);
				dec.setPort((Integer) object[2]);
				dec.setSerialNumber((String) object[3]);
				list.add(dec);
			}
			return list;
		} catch(Exception e) {
			// log
			return null;
		}
	}
	
}
