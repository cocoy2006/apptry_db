package molab.main.java.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import molab.main.java.dao.EmulatorDao;
import molab.main.java.dao.ServerDao;
import molab.main.java.entity.T_Emulator;
import molab.main.java.entity.T_Server;
import molab.main.java.util.Status;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmulatorService {

	@Autowired
	private EmulatorDao emulatorDao;
	
	@Autowired
	private ServerDao serverDao;

	public T_Emulator load(int emulatorId) {
		return emulatorDao.load(emulatorId);
	}

	public List<T_Emulator> loadAll(int serverId) {
		return emulatorDao.loadAll(serverId);
	}
	
	public List<T_Emulator> loadMinimumMonthClicks(int num) {
		return emulatorDao.loadMinimumMonthClicks(num);
	}
	
	public List<String> loadAllOs() {
		List<String> os = emulatorDao.loadAllOs();
		if(os != null && os.size() != 0) {
			List<String> list = new ArrayList<String>();
			for(int i = 0; i < os.size(); i++) {
				list.add(os.get(i));
			}
			return list;
		}
		return null;
	}
	
	public List<String> loadAllDpi() {
		List<String> dpi = emulatorDao.loadAllDpi();
		if(dpi != null && dpi.size() != 0) {
			List<String> list = new ArrayList<String>();
			for(int i = 0; i < dpi.size(); i++) {
				list.add(dpi.get(i));
			}
			return list;
		}
		return null;
	}
	
	public List loadEmulatorsDynatree(String os, String dpi) {
		DetachedCriteria dc = DetachedCriteria.forClass(T_Emulator.class);
		if(os != null && os != "") {
			String[] oss = os.split(",");
			dc.add(Restrictions.in("os", oss));
		}
		if(dpi != null && dpi != "") {
			String[] dpis = dpi.split(",");
			dc.add(Restrictions.in("dpi", dpis));
		}
		
		List emulators = emulatorDao.loadEmulatorsDynatree(dc);
		
		if(emulators != null & emulators.size() != 0) {
			List<T_Server> serverList = serverDao.loadAll();
			Map<Integer, String> serverMap = new HashMap<Integer, String>();
			for(T_Server server : serverList) {
				serverMap.put(server.getId(), 
					server.getIpAddress().concat(":").concat(String.valueOf(server.getPort())));
			}
			
			List list = new ArrayList();
			Map map = new HashMap();
			for(int i = 0; i < emulators.size(); i++) {
				T_Emulator emulator = (T_Emulator) emulators.get(i);
				String serverInfo = serverMap.get(emulator.getServer_id())
						.concat("/")
						.concat(emulator.getSerialNumber());
				String dpiTitle = emulator.getDpi()
						.concat("(")
						.concat(serverInfo)
						.concat(")");
				String dpiKey = String.valueOf(emulator.getId())
						.concat("/")
						.concat(serverInfo);
				if(map.containsKey(emulator.getOs())) {
					Map osMap = (Map) map.get(emulator.getOs());
					List dpiList = (List) osMap.get("children");
					Map dpiMap = new HashMap();
					dpiMap.put("title", dpiTitle);
					dpiMap.put("key", dpiKey);
					dpiList.add(dpiMap);
				} else {
					Map osMap = new HashMap();
					osMap.put("title", emulator.getOs());
					osMap.put("key", emulator.getOs());
					osMap.put("isFolder", true);
					List dpiList = new ArrayList();
					Map dpiMap = new HashMap();
					dpiMap.put("title", dpiTitle);
					dpiMap.put("key", dpiKey);
					dpiList.add(dpiMap);
					osMap.put("children", dpiList);
					map.put(emulator.getOs(), osMap);
				}
			}
			// fill list with map's entrySet.
			Collection c = map.values();
			Iterator iter = c.iterator();
			while(iter.hasNext()) {
				list.add(iter.next());
			}
			return list;
		}
		return null;
	}

	public int save(int serverId, String serialNumber, String manufacturer,
			String model, String os, int width, int height) {
		try {
			T_Emulator emulator = new T_Emulator();
			emulator.setServer_id(serverId);
			emulator.setSerialNumber(serialNumber);
			emulator.setManufacturer(manufacturer);
			emulator.setModel(model);
			emulator.setOs(os);
			emulator.setWidth(width);
			emulator.setHeight(height);
			emulator.setDpi(String.valueOf(width).concat("*").concat(String.valueOf(height)));
			emulator.setState(Status.IDLE);
			return emulatorDao.save(emulator);
		} catch (Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int saveOrUpdate(int id, String serialNumber, String manufacturer, 
			String model, String os, int width, int height) {
		try {
			T_Emulator emulator = emulatorDao.load(id);
			if(emulator != null) {
				emulator.setSerialNumber(serialNumber);
				emulator.setManufacturer(manufacturer);
				emulator.setModel(model);
				emulator.setOs(os);
				emulator.setWidth(width);
				emulator.setHeight(height);
				emulatorDao.saveOrUpdate(emulator);
				return Status.SUCCESS;
			} else {
				throw new Exception("server with specified id does not exist.");
			}
		} catch(Exception e) {
			// log
			return Status.ERROR;
		}
	}
	
	public int update(T_Emulator emulator) {
		try {
			emulatorDao.saveOrUpdate(emulator);
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}

}
