package molab.main.java.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.component.VisitListComponent;
import molab.main.java.entity.T_Visit;
import molab.main.java.service.VisitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/visit")
public class VisitWeb {
	
	@Autowired
	private VisitService visitService;
	
	@ResponseBody
	@RequestMapping(value = "/create/")
	public String create(HttpServletRequest request) {
		T_Visit visit = new T_Visit();
		visit.setApplication_id(Integer.parseInt(request.getParameter("applicationId")));
		visit.setIpAddress(request.getParameter("ipAddress"));
		visit.setMacAddress(request.getParameter("macAddress"));
		visit.setFromUrl(request.getParameter("fromUrl"));
		visit.setLoginTime(Long.parseLong(request.getParameter("loginTime")));
		visit.setDurationTime(Long.parseLong(request.getParameter("durationTime")));
		visit.setUserAgent(request.getParameter("userAgent"));
		int visitId = visitService.save(visit);
		return new Gson().toJson(visitId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/list/")
	public String list(HttpServletRequest request) {
		int developerId = Integer.parseInt(request.getParameter("developerId"));
		List<VisitListComponent> list = visitService.loadList(developerId);
		if(list != null && list.size() > 0) {
			return new Gson().toJson(list);
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/detail/")
	public String detail(HttpServletRequest request) {
		int applicationId = Integer.parseInt(request.getParameter("applicationId"));
		List<T_Visit> list = visitService.loadDetail(applicationId);
		if(list != null && list.size() > 0) {
			return new Gson().toJson(list);
		}
		return "";
	}
	
}
