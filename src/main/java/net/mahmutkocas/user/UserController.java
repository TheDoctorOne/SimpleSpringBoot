package net.mahmutkocas.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.mahmutkocas.common.GenericResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/1.0/users/register")
	public GenericResponse registerUser(@RequestBody UserEntity user) {
		if(userService.isUserExist(user.getUsername())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists!");
		}
		userService.saveUser(user);
		return new GenericResponse("User registered successfully!");
	}

	@PostMapping("/api/1.0/users/login")
	public GenericResponse loginUser(@RequestBody UserEntity user) {
		if(userService.checkPassword(user)) {			
			return new GenericResponse("Login Successful!");
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect!");
	}
	
}
