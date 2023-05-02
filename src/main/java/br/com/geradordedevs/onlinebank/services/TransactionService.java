package br.com.geradordedevs.onlinebank.services;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;

public interface TransactionService {

    TransactionEntity saveTransaction(TransactionEntity transactionEntity);

    TransactionEntity findById(Long id);

    Iterable<TransactionEntity> getTransactions();
}
