package lab.spring.mvc5.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	public String sayHello(@RequestParam(value="name",required=false,defaultValue="Hello") String name,Model model) {
		model.addAttribute("name",name);
		return "hello";
	}

}
