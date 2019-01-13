package lab.spring.mvc5.hello.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lab.spring.mvc5.hello.exception.CustomException;
import lab.spring.mvc5.hello.model.User;
import lab.spring.mvc5.hello.service.UserService;

@Controller
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @GetMapping("/")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("users", userService.list());
        throw new CustomException("E888", "This is custom error message");
        //return "editUsers";
    }
     
    @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }
 
    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                            BindingResult result, Model model) {
 
        if (result.hasErrors()) {
            model.addAttribute("users", userService.list());
            return "editUsers";
        }
 
        userService.save(user);
        return "redirect:/";
    }
    
    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(CustomException ex) {
    	ModelAndView model = new ModelAndView("error/generic_error");
    	model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;

    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
    	ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errMsg", "this is Exception.class");

		return model;

    }
}