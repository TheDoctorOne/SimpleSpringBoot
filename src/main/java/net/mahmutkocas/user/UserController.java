package net.mahmutkocas.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.mahmutkocas.common.GenericResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/1.0/users/register")
	public GenericResponse registerUser(@RequestBody UserEntity user) {
		userService.saveUser(user);
		return new GenericResponse("User registered successfully!");
	}
	

	@PostMapping("/api/1.0/users/login")
	public GenericResponse loginUser(@RequestBody UserEntity user) {
		if(userService.checkPassword(user)) {			
			return new GenericResponse("Login Successful!");
		}
		return new GenericResponse("Password incorrect!");
	}
}
