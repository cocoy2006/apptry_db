package molab.main.java.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Emulator;
import molab.main.java.service.EmulatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/emulator")
public class EmulatorWeb {

	@Autowired
	private EmulatorService emulatorService;

	@ResponseBody
	@RequestMapping(value = "/{id}")
	public String load(@PathVariable int id) {
		T_Emulator emulator = emulatorService.load(id);
		return new Gson().toJson(emulator);
	}
	
	@ResponseBody
	@RequestMapping(value = "/minimumMonthClicks/{num}")
	public String loadMinimumMonthClicks(@PathVariable int num) {
		List<T_Emulator> list = emulatorService.loadMinimumMonthClicks(num);
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadAllOs")
	public String loadAllOs() {
		List<String> list = emulatorService.loadAllOs();
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadAllDpi")
	public String loadAllDpi() {
		List<String> list = emulatorService.loadAllDpi();
		return new Gson().toJson(list);
	}	
	
	@ResponseBody
	@RequestMapping(value = "/loadEmulatorsDynatree")
	public String loadEmulatorsDynatree(HttpServletRequest request) {
		String os = request.getParameter("os");
		String dpi = request.getParameter("dpi");
		List list = emulatorService.loadEmulatorsDynatree(os, dpi);
		return new Gson().toJson(list);
	}

	@ResponseBody
	@RequestMapping(value = "/create/{serverId}/{serialNumber}/{manufacturer}/{model}/{os}/{width}/{height}")
	public String create(@PathVariable int serverId,
			@PathVariable String serialNumber,
			@PathVariable String manufacturer, @PathVariable String model,
			@PathVariable String os, @PathVariable int width,
			@PathVariable int height) {
		if(manufacturer.indexOf("+") > -1)
			manufacturer = manufacturer.replaceAll("\\+", " ");
		if(model.indexOf("+") > -1)
			model = model.replaceAll("\\+", " ");
		if(os.indexOf("+") > -1)
			os = os.replaceAll("\\+", " ");
		int emulatorId = emulatorService.save(serverId, serialNumber,
				manufacturer, model, os, width, height);
		return new Gson().toJson(emulatorId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{serialNumber}/{manufacturer}/{model}/{os}/{width}/{height}")
	public String update(@PathVariable int id,
			@PathVariable String serialNumber,
			@PathVariable String manufacturer, @PathVariable String model,
			@PathVariable String os, @PathVariable int width,
			@PathVariable int height) {
		if(manufacturer.indexOf("+") > -1)
			manufacturer = manufacturer.replaceAll("\\+", " ");
		if(model.indexOf("+") > -1)
			model = model.replaceAll("\\+", " ");
		if(os.indexOf("+") > -1)
			os = os.replaceAll("\\+", " ");
		int result = emulatorService.saveOrUpdate(id, serialNumber, manufacturer, model, os, width, height);
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/")
	public String update(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		T_Emulator emulator = emulatorService.load(id);
		if(emulator != null) {
			String monthClicks = request.getParameter("monthClicks");
			if(monthClicks != null) {
				emulator.setMonthClicks(Integer.parseInt(monthClicks));
			}
			String state = request.getParameter("state");
			if(state != null) {
				emulator.setState(Integer.parseInt(state));
			}
		}
		int result = emulatorService.update(emulator);
		return new Gson().toJson(result);
	}

}
