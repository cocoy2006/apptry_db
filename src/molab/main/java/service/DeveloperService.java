package molab.main.java.service;

import molab.main.java.dao.DeveloperDao;
import molab.main.java.entity.T_Developer;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperService {

	@Autowired
	private DeveloperDao developerDao;
	
	public T_Developer load(int id) {
		return developerDao.load(id);
	}
	
	public T_Developer load(String username) {
		return developerDao.load(username);
	}
	
	public int login(String username, String password) {
		Object obj = developerDao.load(username);
		if(obj != null) {
			T_Developer user = (T_Developer) obj;
			if(password.equalsIgnoreCase(user.getPassword())) {
				return user.getState();
			} else {
				return Status.PASSWORD_NOT_MATCH;
			}
		} else {
			return Status.USER_NOT_EXIST;
		}
	}
		
	public int check(String username) {
		try {
			T_Developer user = developerDao.load(username);
			if(user == null) {
				return Status.SUCCESS;
			} else {
				return Status.USER_EXIST;
			}
		} catch(Exception e) {
			return Status.ERROR;
		}
	}
	
	public int register(T_Developer user/*, String fromUrl*/) {
		Object obj = developerDao.load(user.getUsername());
		if(obj == null) {
			try {
				// create new user
				int id = developerDao.save(user);
				// send active e-mail to user // move to project apptry
//				Mail mail = new Mail(user.getUsername(), user.getEmail(), fromUrl);
//				mail.send();
				return id;
			} catch(Exception e) {
				return Status.ERROR;
			}
		} else {
			return Status.USER_EXIST;
		}
	}
	
	public int active(String username, String email) {
		Object obj = developerDao.load(username);
		if(obj != null) {
			T_Developer user = (T_Developer) obj;
			if(email.equals(user.getEmail())) {
				user.setState(Status.USER_NORMAL);
				developerDao.saveOrUpdate(user);
				return Status.SUCCESS;
			}
		}
		return Status.ERROR_SYSTEM;
	}
	
	public int check(String username, String email) {
		try {
			T_Developer user = developerDao.load(username);
			if(user != null) {
				if(email.equals(user.getEmail())) {
					return Status.SUCCESS;
				} else {
					return Status.EMAIL_ERROR;
				}
			} else {
				return Status.USERNAME_ERROR;
			}
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}
	
	public int update(T_Developer user) {
		try {
			developerDao.saveOrUpdate(user);
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}
	
	public int active(String[] ids) {
		try {
			T_Developer user;
			for(String id : ids) {
				int i = Integer.parseInt(id);
				user = developerDao.load(i);
				if(user != null) {
					user.setState(Status.USER_NORMAL);
					developerDao.saveOrUpdate(user);
				}
			}
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}
	
	public int unactive(String[] ids) {
		try {
			T_Developer user;
			for(String id : ids) {
				int i = Integer.parseInt(id);
				user = developerDao.load(i);
				if(user != null) {
					user.setState(Status.USER_UNACTIVED);
					developerDao.saveOrUpdate(user);
				}
			}
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}
	
	public int delete(String[] ids) {
		try {
			T_Developer user;
			for(String id : ids) {
				int i = Integer.parseInt(id);
				user = developerDao.load(i);
				if(user != null) {
					developerDao.delete(user);
				}
			}
			return Status.SUCCESS;
		} catch(Exception e) {
			return Status.ERROR_SYSTEM;
		}
	}
}