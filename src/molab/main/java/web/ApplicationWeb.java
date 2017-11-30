package molab.main.java.web;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import molab.main.java.entity.T_Application;
import molab.main.java.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/application")
public class ApplicationWeb {
	
	@Autowired
	private ApplicationService applicationService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}")
	public String load(@PathVariable int id) {
		T_Application application = applicationService.load(id);
		return new Gson().toJson(application);
	}
	
	@ResponseBody
	@RequestMapping(value = "/load/{md5}")
	public String loadMd5(@PathVariable String md5) {
		T_Application application = applicationService.loadMd5(md5);
		if(application != null) {
			return new Gson().toJson(application);
		}
		return new Gson().toJson(0);
	}

	@ResponseBody
	@RequestMapping(value = "/loadAll")
	public String loadAll() {
		List list = applicationService.loadAll();
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{name}/{packageName}/{startActivity}")
	public String create(@PathVariable String name,
			@PathVariable String packageName, @PathVariable String startActivity) {
		int applicationId = applicationService.save(name, packageName, startActivity);
		return new Gson().toJson(applicationId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/")
	public String createWithFullInfo(HttpServletRequest request) throws SerialException, SQLException {
		String md5 = request.getParameter("md5");
		String name = request.getParameter("name");
		String aliasName = request.getParameter("aliasName");
		long size = Long.parseLong(request.getParameter("size"));
		String packageName = request.getParameter("packageName");
		String version = request.getParameter("version");
		String os = request.getParameter("os");
		String startActivity = request.getParameter("startActivity");
		Blob icon = null;
		int applicationId = applicationService.save(md5, name, 
				aliasName, size,
				packageName, version,
				os, startActivity, icon);
		return new Gson().toJson(applicationId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{name}/{packageName}/{startActivity}")
	public String update(@PathVariable int id, @PathVariable String name,
			@PathVariable String packageName, @PathVariable String startActivity) {
		int result = applicationService.saveOrUpdate(id, name, packageName, startActivity);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {
		int result = applicationService.delete(id);
		return new Gson().toJson(result);
	}
	
	/**
	 * @param HttpServletRequest request
	 * @return application id if exist or 0 if not.*/
	@ResponseBody
	@RequestMapping(value = "/check/")
	public String check(HttpServletRequest request) {
		int developerId = Integer.parseInt(request.getParameter("developerId"));
		String packageName = request.getParameter("packageName");
		int result = applicationService.check(developerId, packageName);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/remove/{id}/")
	public String remove(@PathVariable int id) {
		int result = applicationService.remove(id);
		return new Gson().toJson(result);
	}
	
}
