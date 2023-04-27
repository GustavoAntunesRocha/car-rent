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

    public UserDTO() {
    }

    public UserDTO(int id, String email, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public static UserDTO fromUser(UserEntity user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getRoles());
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

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
