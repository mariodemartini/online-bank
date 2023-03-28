package br.com.geradordedevs.picpaychallenge.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    private BigDecimal value;
    private Long payer;
    private Long payee;
}
