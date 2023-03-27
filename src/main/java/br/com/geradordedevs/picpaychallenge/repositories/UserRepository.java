package br.com.geradordedevs.picpaychallenge.repositories;

import br.com.geradordedevs.picpaychallenge.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByCpf(String cpf);
}
