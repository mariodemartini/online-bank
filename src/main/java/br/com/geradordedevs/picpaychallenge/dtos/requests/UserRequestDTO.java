package br.com.geradordedevs.picpaychallenge.dtos.requests;

import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String name;
    private DocumentTypeEnum documentTypeEnum;
    private String documentNumber;
    private String email;
    private String password;
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
