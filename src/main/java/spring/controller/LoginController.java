package spring.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.dto.UserDTO;
import spring.model.AdminBean;
import spring.model.UserBean;
import spring.service.AdminService;

@Controller
public class LoginController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	ModelMapper mapper;
	
	@GetMapping("/")
	public ModelAndView showLogin(HttpSession session)
	{
		session.invalidate();
		return new ModelAndView("login", "user", new UserBean());
	}

	@GetMapping("/dologin")
	public String noLogin(HttpSession session)
	{
		return "redirect:/";
	}
	
	@PostMapping("/dologin")
	public String doLogin(@ModelAttribute("user")@Valid UserBean user, BindingResult br, RedirectAttributes model, HttpSession session)
	{
		if(br.hasErrors())
		{
			return "login";
		}
		else
		{
			UserDTO userDto = mapper.map(user, UserDTO.class);
			boolean emailResult = adminService.checkEmail(userDto.getEmail());
			if(emailResult)
			{
				UserDTO dbAdmin=adminService.getUser(userDto);
				if(dbAdmin != null)
				{
					AdminBean adminBean = mapper.map(dbAdmin, AdminBean.class);
					session.setAttribute("loginObj", adminBean);
					model.addFlashAttribute("msg", "Welcome Back...! <br><br>\n"
							+ "        Enjoy this project and try your best in your own!");
					return "redirect:/admin/home";
				}
				else
				{
					model.addFlashAttribute("msg", "Please check your data again.");
					return "redirect:/";
				}
				
			}
			else
			{
				model.addFlashAttribute("msg", "Please check your data again.");
				return "redirect:/";
			}
		}
		
	}
	
	@GetMapping("/register")
	public ModelAndView goRegister() {
		return new ModelAndView("user-registration", "admin", new AdminBean());
	}
	
	
	@PostMapping("/adduser")
	public String doUserAdd(@ModelAttribute("admin")@Valid AdminBean admin, BindingResult br, RedirectAttributes model)
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
				return "redirect:adduser";
			}
			else
			{
				UserDTO adminDto = mapper.map(admin, UserDTO.class);
				int dbAdd = adminService.addUser(adminDto);
				if(dbAdd > 0)
				{
					model.addFlashAttribute("msg", "User has been added!");
					return "redirect:./";
				}
				else
				{
					model.addFlashAttribute("msg", "Registration Failed! Please Register Again!");
					return "redirect:adduser";
				}
			}
		}
	}
	
	@GetMapping("/logout")
	public String logOut()
	{
		return "redirect:/";
	}
	
}
