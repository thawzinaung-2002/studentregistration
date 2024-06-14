package spring.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AdminBean extends UserBean{

	@NotEmpty private String name;
	@NotEmpty private String confirmPassword;
	@NotEmpty private String role;
}
