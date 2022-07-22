package org.geekhub.oleg.user.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class User {
    private long id;

    @NotEmpty(message = "firstname must not be empty")
    @Size(min = 2, message = "firstname size must be greater then 3")
    private String firstName;

    @NotEmpty(message = "lastname must not be empty")
    @Size(min = 2, message = "lastname size must be greater then 3")
    private String lastName;

    @NotEmpty(message = "email must not be empty")
    @Email(message = "email must be valid")
    private String email;

    @NotEmpty(message = "Login cannot be empty")
    private String login;

    @NotEmpty(message = "Password must not be empty")
    private String password;
    private int roleID;

    public User() {
    }

    public User(String firstName, String lastName, String email, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}



