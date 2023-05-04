package br.com.geradordedevs.onlinebank.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @Email(message = "invalid email")
    @NotBlank(message = "invalid field")
    private String email;

    @NotBlank(message = "invalid field")
    @Size(min = 4, max = 8, message = "invalid size")
    private String password;

}
