package lab.spring.mvc5.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@RequestMapping(value="hello",method = RequestMethod.GET)
	public String sayHello(@RequestParam(value="name",required=false,defaultValue="Hello") String name,Model model) {
		System.out.println("inside the controller");
		model.addAttribute("name",name);
		return "hello";
	}

}
