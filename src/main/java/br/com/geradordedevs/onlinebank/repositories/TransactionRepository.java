package br.com.geradordedevs.onlinebank.repositories;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
