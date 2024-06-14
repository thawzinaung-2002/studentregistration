package spring.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.dto.UserDTO;
import spring.model.AdminBean;
import spring.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@Autowired
	ModelMapper mapper;

	@GetMapping("/register")
	public ModelAndView goRegister() {
		return new ModelAndView("user-registration", "admin", new AdminBean());
	}

	@PostMapping("/doregister")
	public String doRegister(@ModelAttribute("admin")@Valid AdminBean admin, BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "user-registration";
		}
		else
		{
			if(!admin.getPassword().equals(admin.getConfirmPassword()))
			{
				model.addFlashAttribute("msg", "Passwords must be match!");
				return "redirect:register";
			}
			else
			{
				UserDTO adminDto = mapper.map(admin, UserDTO.class);
				int dbAdd = service.addUser(adminDto);
				if(dbAdd > 0)
				{
					model.addFlashAttribute("msg", "User has been registered.Please Log in Again!");
					return "redirect:/";
				}
				else
				{
					model.addFlashAttribute("msg", "Registration Failed! Please Register Again!");
					return "redirect:register";
				}
			}
		}
	}
	
	
	@GetMapping("/courseregister")
	public String goCourseRegister()
	{
		return "course-registration";
	}
	
	@GetMapping("/studentregister")
	public String goStudentRegister()
	{
		return "student-registration";
	}
	
	
	@GetMapping("/studentlists")
	public String goStudentLists()
	{
		return "student-lists";
	}
	

	@GetMapping("/userlists")
	public String goUserLists()
	{
		return "user-lists";
	}
	

}
