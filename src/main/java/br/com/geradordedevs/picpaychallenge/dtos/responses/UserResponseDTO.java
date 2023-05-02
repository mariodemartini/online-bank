package br.com.geradordedevs.picpaychallenge.dtos.responses;

import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private DocumentTypeEnum documentTypeEnum;
    private String documentNumber;
    private String email;
    private BigDecimal balance;
}
