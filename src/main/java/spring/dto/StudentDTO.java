package spring.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class StudentDTO {

	private int id;
	private String name;
	private String dob;
	private String gender;
	private String phone;
	private String education;
	private ArrayList<String> courses;
	private MultipartFile photo;
	private String base64Image;
	
}
