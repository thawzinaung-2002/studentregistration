package spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.dto.CourseDTO;
import spring.dto.StudentDTO;
import spring.dto.UserDTO;
import spring.model.AdminBean;
import spring.model.CourseBean;
import spring.model.StudentBean;
import spring.service.AdminService;
import spring.service.CourseService;
import spring.service.StudentService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	
	@Autowired
	ModelMapper mapper;
	
	
	@GetMapping("/home")
	public String doWelcome()
	{
		return "welcome";
	}
	
	
	@GetMapping("/lists")
	public String goUserLists(ModelMap model)
	{
		List<UserDTO> admins = adminService.getAll();
		model.addAttribute("admins", admins);
		return "user-lists";
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
				int dbAdd = adminService.addUser(adminDto);
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
	
	@GetMapping("/register")
	public ModelAndView goRegister() {
		return new ModelAndView("user-add", "admin", new AdminBean());
	}
	
	@PostMapping("/adduser")
	public String doUserAdd(@ModelAttribute("admin")@Valid AdminBean admin, BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "user-add";
		}
		else
		{
			if(!admin.getPassword().equals(admin.getConfirmPassword()))
			{
				model.addFlashAttribute("msg", "Passwords must be match!");
				return "redirect:adduser";
			}
			else
			{
				UserDTO adminDto = mapper.map(admin, UserDTO.class);
				int dbAdd = adminService.addUser(adminDto);
				if(dbAdd > 0)
				{
					model.addFlashAttribute("msg", "User has been added!");
					return "redirect:home";
				}
				else
				{
					model.addFlashAttribute("msg", "Registration Failed! Please Register Again!");
					return "redirect:adduser";
				}
			}
		}
	}
	
	@GetMapping("/update/{id}")
	public String showEditUser(@PathVariable("id")int id, ModelMap model)
	{
		UserDTO dbRs = adminService.getUserById(id);
		AdminBean admin = mapper.map(dbRs, AdminBean.class);
		model.addAttribute("admin", admin);
		return "user-update";
	}
	
	@PostMapping("/update/doupdate")
	public String doUpdate(@ModelAttribute("admin")@Valid AdminBean admin,BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "redirect:../update/"+admin.getId();
		}
		UserDTO userDto = mapper.map(admin, UserDTO.class);
		int dbRs = adminService.updateUser(userDto);
		model.addFlashAttribute("msg", "User has been updated");
		return "redirect:../home";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id")int id)
	{
//		adminService.deleteById(id);
		return "redirect:../lists";
	}
	
}
