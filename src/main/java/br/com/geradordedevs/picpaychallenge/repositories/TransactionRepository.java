package br.com.geradordedevs.picpaychallenge.repositories;

import br.com.geradordedevs.picpaychallenge.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
