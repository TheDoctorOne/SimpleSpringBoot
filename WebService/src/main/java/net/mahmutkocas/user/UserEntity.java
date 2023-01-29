package net.mahmutkocas.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "UserTable")
@Entity
public class UserEntity {

	@Id
	private String username;
	
	private String displayName;
	private String password;
	private String mail;
}
