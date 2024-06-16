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

import spring.dto.CourseDTO;
import spring.model.CourseBean;
import spring.service.CourseService;

@Controller
@RequestMapping("/admin/course")
public class CourseController {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	CourseService courseService;
	

	@GetMapping("/register")
	public ModelAndView goCourseRegister()
	{
		return new ModelAndView("course-registration", "course", new CourseBean());
	}
	
	@PostMapping("/add")
	public String addCourse(@ModelAttribute("course")@Valid CourseBean course, BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "course-registration";
		}
		else
		{
			
			CourseDTO courseDto = mapper.map(course, CourseDTO.class);
			int dbRs = courseService.addCourse(courseDto);
			if(dbRs > 0)
			{
				model.addFlashAttribute("msg", "Course has been registered.<br/><br/>Enjoy this project and try your best in your own!");
				return "redirect:../home";
			}
			else
			{
				return "course-registration";
			}
		}
	}
	
}
