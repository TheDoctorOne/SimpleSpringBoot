package net.mahmutkocas.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder pwEncoder;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		pwEncoder = new BCryptPasswordEncoder();
	}
	
	public void saveUser(UserEntity user) {
		user.setPassword(pwEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public boolean checkPassword(UserEntity user) {
		UserEntity storedUser = userRepository.getReferenceById(user.getUsername());
		return pwEncoder.matches(user.getPassword(), storedUser.getPassword());
	}
}
