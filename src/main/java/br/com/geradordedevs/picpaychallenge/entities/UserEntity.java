package br.com.geradordedevs.picpaychallenge.entities;

import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private DocumentTypeEnum documentTypeEnum;
    private String documentNumber;
    private String email;
    private String password;
    private BigDecimal balance;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name=" + name +
                ", documentType='" + documentTypeEnum + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", email=" + email +
                ", balance='" + balance + '\'' +
                '}';
    }

}
