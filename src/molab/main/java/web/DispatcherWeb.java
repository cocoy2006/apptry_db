package molab.main.java.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.component.DispatcherEmulatorComponent;
import molab.main.java.entity.T_Dispatcher;
import molab.main.java.service.DispatcherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/dispatcher")
public class DispatcherWeb {
	
	@Autowired
	private DispatcherService dispatcherService;

//	@ResponseBody
//	@RequestMapping(value = "/create/{developerId}/{applicationId}/{time}/{state}")
//	public String create(@PathVariable int developerId, @PathVariable int applicationId,
//			@PathVariable long time, @PathVariable int state) {
//		T_Dispatcher dispatcher = new T_Dispatcher();
//		dispatcher.setApplication_id(applicationId);
//		dispatcher.setDeveloper_id(developerId);
//		dispatcher.setTime(time);
//		dispatcher.setState(state);
//		int deploymentId = dispatcherService.save(dispatcher);
//		return new Gson().toJson(deploymentId);
//	}
	
	@ResponseBody
	@RequestMapping(value = "/create/")
	public String createWithFullInfo(HttpServletRequest request) {
		T_Dispatcher dispatcher = new T_Dispatcher();
		dispatcher.setDeveloper_id(Integer.parseInt(request.getParameter("developerId")));
		dispatcher.setApplication_id(Integer.parseInt(request.getParameter("applicationId")));
		dispatcher.setTime(Long.parseLong(request.getParameter("time")));
		dispatcher.setState(Integer.parseInt(request.getParameter("state")));
		int deploymentId = dispatcherService.save(dispatcher);
		return new Gson().toJson(deploymentId);
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/update/{id}/{state}")
//	public String updateRest(@PathVariable int id, @PathVariable int state) {
//		T_Dispatcher dispatcher = dispatcherService.load(id);
//		dispatcher.setState(state);
//		int result = dispatcherService.saveOrUpdate(dispatcher);
//		return new Gson().toJson(result);
//	}
	
	@ResponseBody
	@RequestMapping(value = "/update/")
	public String update(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		T_Dispatcher dispatcher = dispatcherService.load(id);
		if(dispatcher != null) {
			int state = Integer.parseInt(request.getParameter("state"));
			dispatcher.setState(state);
		}
		int result = dispatcherService.saveOrUpdate(dispatcher);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadEmulators/{num}")
	public String loadEmulators(@PathVariable int num) {
		List<DispatcherEmulatorComponent> list = dispatcherService.loadEmulators(num);
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadEmulatorsWithApplicationId/{applicationId}")
	public String loadEmulatorsWithApplicationId(@PathVariable int applicationId) {
		List<DispatcherEmulatorComponent> list = dispatcherService.loadEmulatorsWithApplicationId(applicationId);
		return new Gson().toJson(list);
	}
	
}
