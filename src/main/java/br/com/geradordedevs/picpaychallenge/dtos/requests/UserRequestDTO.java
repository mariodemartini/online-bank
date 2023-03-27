package br.com.geradordedevs.picpaychallenge.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String name;
    private String cpf;
    private String email;
    private String password;
    private BigDecimal balance;

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                ", name=" + name +
                ", cpf='" + cpf + '\'' +
                ", email=" + email +
                ", saldo='" + balance + '\'' +
                '}';
    }
}
