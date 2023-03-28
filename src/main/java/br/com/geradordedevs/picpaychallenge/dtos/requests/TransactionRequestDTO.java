package br.com.geradordedevs.picpaychallenge.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    @NotNull(message = "invalid field")
    private BigDecimal value;

    @Min(value = 1, message = "invalid field")
    private Long payer;

    @Min(value = 1, message = "invalid field")
    private Long payee;
}
