package spring.model;

import lombok.Data;

@Data
public class StudentBean {

	private String name;
	private String dob;
	private String gender;
	private String phone;
	private String education;
	private String[] courses;
	private String photo;
	
}
