package site.metacoding.red.domain.users;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Update;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@Setter
@Getter
public class Users {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp createdAt;
	

	public void updateEmailPassword(UpdateDto updateDto) {
		this.email= updateDto.getEmail();
		this.password=updateDto.getPassword();
	}


	public Users(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
