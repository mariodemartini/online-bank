package br.com.geradordedevs.picpaychallenge.services;

import br.com.geradordedevs.picpaychallenge.entities.TransactionEntity;

public interface TransactionService {

    TransactionEntity saveTransaction(TransactionEntity transactionEntity);

    TransactionEntity findById(Long id);

    Iterable<TransactionEntity> getTransactions();
}
