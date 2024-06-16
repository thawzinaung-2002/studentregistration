package spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.dto.StudentDTO;
import spring.model.StudentBean;
import spring.service.CourseService;
import spring.service.StudentService;

@Controller
@RequestMapping("/admin/student")
public class StudentController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	ModelMapper mapper;
	
	
	@GetMapping("/register")
	public ModelAndView goStudentRegister()
	{
		return new ModelAndView("student-registration", "student", new StudentBean());
	}
	
	@GetMapping("/lists")
	public String goStudentLists(ModelMap model)
	{
		List<StudentDTO> dbRs = studentService.getAllStudents();
		model.addAttribute("students", dbRs);
		return "student-lists";
	}
	
	@PostMapping("/doregister")
	public String doStudentRegister(@ModelAttribute("student")@Valid StudentBean student, BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "student-registration";
		}
		else
		{
			if(student.getPhoto().isEmpty())
			{
				model.addFlashAttribute("emptyErr", "Please select your image");
				return "student-registration";
			}
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
	
	@GetMapping("/{stu_id}")
	public String getStudentDetails(@PathVariable("stu_id")int id, ModelMap model)
	{
		StudentDTO dbRs = studentService.getUser(id);
		StudentBean student = mapper.map(dbRs, StudentBean.class);
		model.addAttribute("student", student);
		return "student-details";
	}
	
	@GetMapping("/update/{stu_id}")
	public String showUpdate(@PathVariable("stu_id")int id, ModelMap model)
	{
		StudentDTO dbRs = studentService.getUser(id);
		StudentBean student = mapper.map(dbRs, StudentBean.class);
		model.addAttribute("student", student);
		return "student-update";
	}
	
	@PostMapping("/update/doupdate")
	public String updateStudent(@ModelAttribute("student")@Valid StudentBean student, BindingResult br, RedirectAttributes model)
	{
		if(br.hasErrors())
		{
			return "student-update";
		}
		else
		{
			if(student.getPhoto().isEmpty())
			{
				model.addAttribute("emptyErr", "Please select your image");
				return "student-update";
			}
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
				return "redirect:/admin/home";
			}
			else
			{
				return "student-update";
			}
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable("id")int id)
	{
//		studentService.deleteById(id);
		return "redirect:/admin/student/lists";
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
