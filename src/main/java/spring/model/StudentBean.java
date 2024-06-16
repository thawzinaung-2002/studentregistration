package spring.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StudentBean {

	private int id;
	@NotEmpty private String name;
	@NotEmpty private String dob;
	@NotEmpty private String gender;
	@NotEmpty private String phone;
	@NotEmpty private String education;
	@NotEmpty private List<String> courses;
	@NotNull private MultipartFile photo;
	private String base64Image;
	
}
