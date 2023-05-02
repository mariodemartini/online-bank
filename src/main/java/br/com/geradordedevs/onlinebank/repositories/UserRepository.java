package br.com.geradordedevs.onlinebank.repositories;

import br.com.geradordedevs.onlinebank.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByDocumentNumber(String number);
}
