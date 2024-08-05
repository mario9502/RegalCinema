package bg.softuni.regalcinema.model.dtos.importDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30, message = "Size must be between 3 and 30 symbols")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50 symbols")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 40, message = "Size must be between 3 and 40 symbols")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 40, message = "Size must be between 3 and 40 symbols")
    private String lastName;

    public UserRegisterDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
