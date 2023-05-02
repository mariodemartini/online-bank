package br.com.geradordedevs.picpaychallenge.facades.impl;

import br.com.geradordedevs.picpaychallenge.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.picpaychallenge.entities.TransactionEntity;
import br.com.geradordedevs.picpaychallenge.entities.UserEntity;
import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import br.com.geradordedevs.picpaychallenge.exceptions.TransactionException;
import br.com.geradordedevs.picpaychallenge.exceptions.enums.TransactionEnum;
import br.com.geradordedevs.picpaychallenge.facades.TransactionFacade;
import br.com.geradordedevs.picpaychallenge.mappers.TransactionMapper;
import br.com.geradordedevs.picpaychallenge.mappers.UserMapper;
import br.com.geradordedevs.picpaychallenge.services.AuthorizationService;
import br.com.geradordedevs.picpaychallenge.services.TransactionService;
import br.com.geradordedevs.picpaychallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class TransactionFacadeImpl implements TransactionFacade {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public AuthorizationResponseDTO transaction(TransactionRequestDTO transactionRequestDTO) throws Exception {
        Long idPayer = transactionRequestDTO.getPayer();
        Long idPayee = transactionRequestDTO.getPayee();
        BigDecimal value = transactionRequestDTO.getValue();
        
        UserEntity userPayee = userService.findById(idPayee);

        UserEntity userPayer = validation(idPayer, value);

        userPayer.setId(idPayer);
        userPayer.setBalance(userPayer.getBalance().subtract(value));
        userMapper.toDTO(userService.updateUser(idPayer, userPayer));

        userPayee.setId(idPayee);
        userPayee.setBalance(userPayee.getBalance().add(value));
        userMapper.toDTO(userService.updateUser(idPayee, userPayee));

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setPayer(userPayer.getName());
        transactionEntity.setPayee(userPayee.getName());
        transactionEntity.setValue(value);
        transactionMapper.toDTO(transactionService.saveTransaction(transactionEntity));

        return authorizationService.authorization();
    }

    @Override
    public TransactionResponseDTO findById(Long id) {
        return transactionMapper.toDTO(transactionService.findById(id));
    }

    @Override
    public List<TransactionResponseDTO> getTransactions() {
        return transactionMapper.toListDTO(transactionService.getTransactions());
    }

    private UserEntity validation(Long idPayer, BigDecimal value){
        UserEntity userPayer = userService.findById(idPayer);
        if(userPayer.getDocumentTypeEnum() == DocumentTypeEnum.CNPJ){
            throw new TransactionException(TransactionEnum.INVALID_TRANSACTION);
        } else if (userPayer.getBalance().compareTo(value) < 0) {
            throw new TransactionException(TransactionEnum.INVALID_TRANSACTION);
        } else {
            return userPayer;
        }
    }

}
