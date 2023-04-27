package br.com.antunes.gustavo.carrentproject.model.dto;

import java.util.List;

import br.com.antunes.gustavo.carrentproject.security.Role;
import br.com.antunes.gustavo.carrentproject.security.UserEntity;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    private int id;

    @NotBlank
    private String email;

    private List<Role> roles;

	private long personId;

    public UserDTO() {
    }

    public UserDTO(int id, String email, List<Role> roles, long personId) {
        this.id = id;
        this.email = email;
        this.roles = roles;
		this.personId = personId;
    }

    public static UserDTO fromUser(UserEntity user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getRoles(), user.getPersonId());
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRole(List<Role> roles) {
		this.roles = roles;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long person) {
		this.personId = person;
	}

}
