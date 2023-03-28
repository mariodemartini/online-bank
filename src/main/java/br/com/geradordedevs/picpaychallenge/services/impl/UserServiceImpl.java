package br.com.geradordedevs.picpaychallenge.services.impl;

import br.com.geradordedevs.picpaychallenge.entities.UserEntity;
import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import br.com.geradordedevs.picpaychallenge.exceptions.DocumentException;
import br.com.geradordedevs.picpaychallenge.exceptions.EmailException;
import br.com.geradordedevs.picpaychallenge.exceptions.enums.DocumentEnum;
import br.com.geradordedevs.picpaychallenge.exceptions.enums.EmailEnum;
import br.com.geradordedevs.picpaychallenge.repositories.UserRepository;
import br.com.geradordedevs.picpaychallenge.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<UserEntity> getUsers() {
        log.info("listing users");
        return userRepository.findAll();
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) throws Exception{
        log.info("save a new user {}: ", userEntity);
        if(userRepository.findByEmail(userEntity.getEmail()) != null) {
            throw new EmailException(EmailEnum.INVALID_EMAIL);
        } else if(userRepository.findByDocumentNumber(userEntity.getDocumentNumber()) != null){
            throw new DocumentException(DocumentEnum.INVALID_DOCUMENT);
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity userEntity) {
        log.info("updating user {} - new informations: {} ", id, userEntity);
        userEntity.setId(id);
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        log.info("find user {}", id);
        return userRepository.findById(id).orElse(new UserEntity());
    }

    @Override
    public void deleteUser(Long id) {
        log.info("delete user {}", id);
        userRepository.deleteById(id);
    }
}
