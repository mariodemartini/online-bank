package br.com.geradordedevs.onlinebank.facades;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;

import java.util.List;

public interface TransactionFacade {

    AuthorizationResponseDTO transaction(TransactionRequestDTO transactionRequestDTO) throws Exception;

    TransactionResponseDTO findById(Long id);

    List<TransactionResponseDTO> getTransactions();
}
