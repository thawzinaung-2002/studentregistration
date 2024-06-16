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
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
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
	
	
	@GetMapping("/courseregister")
	public ModelAndView goCourseRegister()
	{
		return new ModelAndView("course-registration", "course", new CourseBean());
	}
	
	
	@PostMapping("/addCourse")
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
	
	@GetMapping("/studentregister")
	public ModelAndView goStudentRegister()
	{
		return new ModelAndView("student-registration", "student", new StudentBean());
	}
	
	@PostMapping("/dostudentregister")
	public String doStudentRegister(@ModelAttribute("student")@Valid StudentBean student, BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "student-registration";
		}
		else
		{
			MultipartFile image = student.getPhoto();
			try {
				String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
				student.setBase64Image(base64Image);
			} catch (IOException e) {
				System.out.println("Image encoder : "+ e.getMessage());
			}
			
			StudentDTO studentDto = mapper.map(student, StudentDTO.class);
		
			int dbRs = studentService.insertUser(studentDto);
			if(dbRs > 0)
			{
				model.addFlashAttribute("msg", "User has been registered.<br/><br/>Enjoy this project and try your best in your own!");
				return "redirect:../home";
			}
			else
			{
				return "student-registration";
			}
			
		}
	}
	
	@GetMapping("/student/{stu_id}")
	public String getStudentDetails(@PathVariable("stu_id")int id, ModelMap model)
	{
		StudentDTO dbRs = studentService.getUser(id);
		StudentBean student = mapper.map(dbRs, StudentBean.class);
		model.addAttribute("student", student);
		return "student-details";
	}
	
	@GetMapping("/student/update/{stu_id}")
	public String showUpdate(@PathVariable("stu_id")int id, ModelMap model)
	{
		StudentDTO dbRs = studentService.getUser(id);
		StudentBean student = mapper.map(dbRs, StudentBean.class);
		model.addAttribute("student", student);
		return "student-update";
	}
	
	@PostMapping("/student/doupdate")
	public String updateStudent(@ModelAttribute("student")@Valid StudentBean student, BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			model.addFlashAttribute(br.toString());
			return "redirect:/admin/student/update/"+student.getId();
		}
		else
		{
			MultipartFile image = student.getPhoto();
			try {
				String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
				student.setBase64Image(base64Image);
			} catch (IOException e) {
				System.out.println("Image encoder : "+ e.getMessage());
			}
			
			StudentDTO studentDto = mapper.map(student, StudentDTO.class);
			int dbRs = studentService.update(studentDto);
			if(dbRs > 0)
			{
				model.addFlashAttribute("msg", "Updated Successfully!");
				return "redirect:../../home";
			}
			else
			{
				return "student-update";
			}
		}
	}
	
	@GetMapping("/delete/student/{id}")
	public String deleteStudent(@PathVariable("id")int id)
	{
		studentService.deleteById(id);
		return "redirect:../../studentlists";
	}
	
	
	@GetMapping("/studentlists")
	public String goStudentLists(ModelMap model)
	{
		List<StudentDTO> dbRs = studentService.getAllStudents();
		model.addAttribute("students", dbRs);
		return "student-lists";
	}
	

	@GetMapping("/userlists")
	public String goUserLists(ModelMap model)
	{
		List<UserDTO> admins = adminService.getAll();
		model.addAttribute("admins", admins);
		return "user-lists";
	}
	
	
	@GetMapping("/adduser")
	public ModelAndView showAddUser()
	{
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
	
	@GetMapping("/update/user/{id}")
	public String showEditUser(@PathVariable("id")int id, ModelMap model)
	{
		UserDTO dbRs = adminService.getUserById(id);
		AdminBean admin = mapper.map(dbRs, AdminBean.class);
		model.addAttribute("admin", admin);
		return "user-update";
	}
	
	@PostMapping("/update/user/doupdate")
	public String doUpdate(@ModelAttribute("admin")@Valid AdminBean admin,BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "redirect:./"+admin.getId();
		}
		AdminBean adminBean = admin;
		UserDTO userDto = mapper.map(adminBean, UserDTO.class);
		int dbRs = adminService.updateUser(userDto);
		model.addFlashAttribute("msg", "User has been updated");
		return "redirect:../../../home";
	}
	
	@GetMapping("/delete/user/{id}")
	public String deleteUser(@PathVariable("id")int id)
	{
		adminService.deleteById(id);
		return "redirect:./../../userlists";
	}
	
	@ModelAttribute("educations")
	public ArrayList<String> getInfo()
	{
		ArrayList<String> educations = new ArrayList<>();
		educations.add("High School Diploma");
		educations.add("Bachelor of Information Technology");
		educations.add("Diploma in IT");
		educations.add("Bachelor of Computer Science");
		
		return educations;
	}
	
	
	@ModelAttribute("courses")
	public Map<String, String> getCourses()
	{
		Map<String, String> courses = courseService.getCourses();
		return courses;
	}
}
