package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.repositories.TransactionRepository;
import br.com.geradordedevs.onlinebank.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionEntity saveTransaction(TransactionEntity transactionEntity){
        log.info("creating new transaction - {}", transactionEntity);
        return transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionEntity findById(Long id){
        log.info("find transaction {}",id);
        return transactionRepository.findById(id).orElse(new TransactionEntity());
    }

    @Override
    public Iterable<TransactionEntity> getTransactions(){
        log.info("listing all transactions");
        return transactionRepository.findAll();
    }
}
