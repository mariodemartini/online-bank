package br.com.geradordedevs.picpaychallenge.controllers;

import br.com.geradordedevs.picpaychallenge.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.picpaychallenge.facades.TransactionFacade;
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
    public AuthorizationResponseDTO transaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO) throws Exception {
        return transactionFacade.transaction(transactionRequestDTO);
    }

    @GetMapping
    public List<TransactionResponseDTO> listUsers(){
        return transactionFacade.getTransactions();
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO findById(@PathVariable Long id){
        return transactionFacade.findById(id);
    }
}
