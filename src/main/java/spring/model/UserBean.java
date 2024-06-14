package spring.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserBean {

	@NotEmpty private String email;
	@NotEmpty private String password;
	
}
