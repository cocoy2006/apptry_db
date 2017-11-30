package molab.main.java.web;

import javax.servlet.http.HttpServletRequest;

import molab.main.java.entity.T_Product;
import molab.main.java.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/product")
public class ProductWeb {

	@Autowired
	private ProductService productService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/")
	public String load(@PathVariable int id) {
		T_Product product = productService.load(id);
		return new Gson().toJson(product);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		int data = productService.delete(ids);
		return new Gson().toJson(data);
	}
	
}
