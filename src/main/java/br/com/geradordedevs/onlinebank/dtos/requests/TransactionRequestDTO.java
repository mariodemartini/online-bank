package br.com.geradordedevs.onlinebank.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    @NotNull
    private BigDecimal value;

    @Min(value = 1, message = "invalid field")
    private Long payer;

    @Min(value = 1, message = "invalid field")
    private Long payee;
}
