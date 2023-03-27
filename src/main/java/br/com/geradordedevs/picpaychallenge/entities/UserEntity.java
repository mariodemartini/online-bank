package br.com.geradordedevs.picpaychallenge.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private BigDecimal balance;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name=" + name +
                ", cpf='" + cpf + '\'' +
                ", email=" + email +
                ", saldo='" + balance + '\'' +
                '}';
    }

}
