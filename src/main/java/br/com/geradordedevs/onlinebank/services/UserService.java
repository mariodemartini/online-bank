package br.com.geradordedevs.onlinebank.services;

import br.com.geradordedevs.onlinebank.entities.UserEntity;

public interface UserService {

    Iterable<UserEntity> getUsers();

    UserEntity saveUser(UserEntity userEntity) throws Exception;

    UserEntity updateUser(Long id, UserEntity userEntity);

    UserEntity findById(Long id);

    void deleteUser(Long id);
}
