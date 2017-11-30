package molab.test.java.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/test")
public class TestWeb {
	
	@RequestMapping(value = "/try")
	public String apptryIndex() {
		return "testTry";
	}

}
