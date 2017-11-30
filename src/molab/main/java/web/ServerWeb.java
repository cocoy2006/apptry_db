package molab.main.java.web;

import java.util.List;

import molab.main.java.entity.T_Server;
import molab.main.java.service.ServerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/server")
public class ServerWeb {
	
	@Autowired
	private ServerService serverService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}")
	public String load(@PathVariable int id) {
		T_Server server = serverService.load(id);
		return new Gson().toJson(server);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadAll")
	public String loadAll() {
		List list = serverService.loadAll();
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/create/{ipAddress}/{port}")
	public String create(@PathVariable String ipAddress, @PathVariable int port) {
		int serverId = serverService.save(ipAddress, port);
		return new Gson().toJson(serverId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{ipAddress}/{port}")
	public String update(@PathVariable int id, @PathVariable String ipAddress, @PathVariable int port) {
		int result = serverService.saveOrUpdate(id, ipAddress, port);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{oldIpAddress}/{newIpAddress}")
	public String update(@PathVariable String oldIpAddress, @PathVariable String newIpAddress) {
		int result = serverService.saveOrUpdate(oldIpAddress, newIpAddress);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {
		int result = serverService.delete(id);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/{ipAddress}")
	public String delete(@PathVariable String ipAddress) {
		int result = serverService.delete(ipAddress);
		return new Gson().toJson(result);
	}
	
}
