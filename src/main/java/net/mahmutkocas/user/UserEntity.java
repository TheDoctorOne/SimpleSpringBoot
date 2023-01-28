package net.mahmutkocas.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "UserTable")
@Entity
public class UserEntity {

	@GeneratedValue
	private long id;

	@Id
	private String username;
	private String password;
	private String mail;
}
