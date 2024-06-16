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
	
	@GetMapping("/home")
	public String doWelcome()
	{
		return "welcome";
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
					return "redirect:home";
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
	
}
