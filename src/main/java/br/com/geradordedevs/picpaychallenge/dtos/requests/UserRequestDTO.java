package br.com.geradordedevs.picpaychallenge.dtos.requests;

import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "invalid field")
    @Size(min=2, max=15, message = "invalid size")
    private String name;

    @NotBlank(message = "invalid field")
    private DocumentTypeEnum documentTypeEnum;

    @NotBlank(message = "invalid field")
    @Size(min=14, max=18, message = "invalid size")
    private String documentNumber;

    @Email(message = "invalid email")
    @NotBlank(message = "invalid field")
    private String email;

    @NotBlank(message = "invalid field")
    @Size(min = 4, max = 8, message = "invalid size")
    private String password;

    @NotNull(message = "invalid field")
    private BigDecimal balance;

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                ", name=" + name +
                ", documentType='" + documentTypeEnum + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", email=" + email +
                ", balance='" + balance + '\'' +
                '}';
    }
}
