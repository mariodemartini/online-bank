package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.exceptions.TransactionException;
import br.com.geradordedevs.onlinebank.exceptions.enums.TransactionEnum;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import br.com.geradordedevs.onlinebank.mappers.TransactionMapper;
import br.com.geradordedevs.onlinebank.mappers.UserMapper;
import br.com.geradordedevs.onlinebank.services.AuthorizationService;
import br.com.geradordedevs.onlinebank.services.TransactionService;
import br.com.geradordedevs.onlinebank.services.UserService;
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
