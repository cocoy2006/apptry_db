package molab.main.java.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Deployment;
import molab.main.java.service.DeploymentService;
import molab.main.java.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/deployment")
public class DeploymentWeb {
	
	@Autowired
	private DeploymentService deploymentService;

	@ResponseBody
	@RequestMapping(value = "/loadAll")
	public String loadAll() {
		List list = deploymentService.loadAll();
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadAll/{applicationId}")
	public String loadAll(@PathVariable int applicationId) {
		List list = deploymentService.loadAll(applicationId);
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{emulatorId}/{applicationId}")
	public String create(@PathVariable int emulatorId, @PathVariable int applicationId) {
		int deploymentId = deploymentService.save(emulatorId, applicationId);
		return new Gson().toJson(deploymentId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/")
	public String createWithFullInfo(HttpServletRequest request) {
		T_Deployment deployment = new T_Deployment();
		deployment.setDispatcher_id(Integer.parseInt(request.getParameter("dispatcherId")));
		deployment.setApplication_id(Integer.parseInt(request.getParameter("applicationId")));
		deployment.setEmulator_id(Integer.parseInt(request.getParameter("emulatorId")));
		deployment.setState(request.getParameter("state"));
		int id = deploymentService.save(deployment);
		return new Gson().toJson(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{emulatorId}/{applicationId}")
	public String update(@PathVariable int id, @PathVariable int emulatorId,
			@PathVariable int applicationId) {
		int result = deploymentService.saveOrUpdate(id, emulatorId, applicationId);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/")
	public String updateState(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		T_Deployment deployment = deploymentService.load(id);
		if(deployment != null) {
			String state = request.getParameter("state");
			if(state != null) {
				deployment.setState(state);
			}
			
		}
		int result = deploymentService.saveOrUpdate(deployment);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {
		int result = deploymentService.delete(id);
		return new Gson().toJson(result);
	}
	
}
