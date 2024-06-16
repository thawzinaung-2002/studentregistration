package spring.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CourseBean {
	
	@NotEmpty private String id;
	@NotEmpty private String name;

}
