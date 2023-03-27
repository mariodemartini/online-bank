package br.com.geradordedevs.picpaychallenge.services;

import br.com.geradordedevs.picpaychallenge.entities.UserEntity;

public interface UserService {

    Iterable<UserEntity> getUsers();
    UserEntity saveUser(UserEntity userEntity) throws Exception;
    UserEntity updateUser(Long id, UserEntity userEntity);
    UserEntity findById(Long id);
    void deleteUser(Long id);
}
