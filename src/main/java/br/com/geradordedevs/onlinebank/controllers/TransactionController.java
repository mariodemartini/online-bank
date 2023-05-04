package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping
    public AuthorizationResponseDTO transaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO,
                                                @RequestHeader(required = false, value = "token") String token) throws Exception {
        return transactionFacade.transaction(transactionRequestDTO, token);
    }

    @GetMapping
    public List<TransactionResponseDTO> listTransactions(@RequestHeader(required = false, value = "token") String token){
        return transactionFacade.getTransactions(token);
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO findById(@PathVariable Long id,
                                           @RequestHeader(required = false, value = "token") String token){
        return transactionFacade.findById(id, token);
    }
}
