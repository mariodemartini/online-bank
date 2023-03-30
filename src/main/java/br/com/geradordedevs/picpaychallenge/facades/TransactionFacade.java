package br.com.geradordedevs.picpaychallenge.facades;

import br.com.geradordedevs.picpaychallenge.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.TransactionResponseDTO;

import java.util.List;

public interface TransactionFacade {

    AuthorizationResponseDTO transaction(TransactionRequestDTO transactionRequestDTO) throws Exception;

    TransactionResponseDTO findById(Long id);

    List<TransactionResponseDTO> getTransactions();
}
