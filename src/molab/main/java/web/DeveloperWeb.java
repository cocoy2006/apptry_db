package molab.main.java.web;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Developer;
import molab.main.java.service.DeveloperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/user")
public class DeveloperWeb {

	private static final Logger LOG = Logger.getLogger(DeveloperWeb.class.getName());
	
	@Autowired
	private DeveloperService developerService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/")
	public String load(@PathVariable int id) {
		T_Developer developer = developerService.load(id);
		if(developer != null) {
			developer.setPassword("");
			return new Gson().toJson(developer);
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/{username}")
	public String load(@PathVariable String username) {
		T_Developer developer = developerService.load(username);
		if(developer != null) {
			developer.setPassword("");
			return new Gson().toJson(developer);
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		String username = request.getParameter("l_u");
		String password = request.getParameter("l_p");
		int data = developerService.login(username, password);
//		if(data == Status.USER_NORMAL) {
//			T_Developer user = developerService.load(username);
//			request.getSession().setAttribute("developer", user);
//		}
		return new Gson().toJson(data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/check/{username}")
	public String check(@PathVariable String username) {
		int data = developerService.check(username);
		return new Gson().toJson(data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/signup")
	public String signup(HttpServletRequest request) {
		T_Developer user = new T_Developer();
		user.setUsername(request.getParameter("r_u"));
		user.setPassword(request.getParameter("r_p"));
		user.setEmail(request.getParameter("r_email"));
		user.setLeftClicks(Integer.parseInt(request.getParameter("r_left_clicks")));
		user.setRegisterTime(Long.parseLong(request.getParameter("r_register_time")));
		user.setExpiration(Long.parseLong(request.getParameter("r_expiration")));
		user.setIsRenewal(Integer.parseInt(request.getParameter("r_is_renewal")));
		user.setState(Integer.parseInt(request.getParameter("r_state")));
		int id = developerService.register(user);
		return new Gson().toJson(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/")
	public String update(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		T_Developer user = developerService.load(id);
		if(user != null) {
			String password = request.getParameter("password");
			if(password != null) {
				user.setPassword(password);
			}
			String leftClicksString = request.getParameter("leftClicks");
			if(leftClicksString != null) {
				user.setLeftClicks(Integer.parseInt(leftClicksString));
			}
		}
		int result = developerService.update(user);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/active/{username}/{email:.*}/")
	public String active(@PathVariable String username, @PathVariable("email") String email) {
		int data = developerService.active(username, email);
		return new Gson().toJson(data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/active")
	public String active(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		int data = developerService.active(ids);
		return new Gson().toJson(data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/unactive")
	public String unactive(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		int data = developerService.unactive(ids);
		return new Gson().toJson(data);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		int data = developerService.delete(ids);
		return new Gson().toJson(data);
	}
	
}
