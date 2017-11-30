package molab.main.java.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Order;
import molab.main.java.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/order")
public class OrderWeb {
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/")
	public String load(@PathVariable long id) {
		T_Order order = orderService.load(id);
		return new Gson().toJson(order);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadAll/{developerId}/")
	public String loadAll(@PathVariable int developerId) {
		List list = orderService.loadAll(developerId);
		return new Gson().toJson(list);
	}

	@ResponseBody
	@RequestMapping(value = "/create/")
	public String create(HttpServletRequest request) throws Exception {
		T_Order order = new T_Order();
		long id = Long.parseLong(request.getParameter("id"));
		order.setId(id);
		order.setProduct_id(Integer.parseInt(request.getParameter("productId")));
		order.setDeveloper_id(Integer.parseInt(request.getParameter("developerId")));
		order.setTime(Long.parseLong(request.getParameter("time")));
		order.setState(Integer.parseInt(request.getParameter("state")));
		orderService.save(order);
		return new Gson().toJson(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/{id}/{newState}/")
	public String update(@PathVariable long id, @PathVariable int newState) throws Exception {
		T_Order order = orderService.load(id);
		order.setState(newState);
		int result = orderService.saveOrUpdate(order);
		return new Gson().toJson(result);
	}
	
}
