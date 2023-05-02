package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.exceptions.DocumentException;
import br.com.geradordedevs.onlinebank.exceptions.EmailException;
import br.com.geradordedevs.onlinebank.exceptions.enums.DocumentEnum;
import br.com.geradordedevs.onlinebank.exceptions.enums.EmailEnum;
import br.com.geradordedevs.onlinebank.repositories.UserRepository;
import br.com.geradordedevs.onlinebank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Iterable<UserEntity> getUsers() {
        log.info("listing users");
        return userRepository.findAll();
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) throws Exception{
        log.info("save a new user {}: ", userEntity);

        userEntity.setDocumentNumber(documentNumberFormatting(userEntity.getDocumentNumber()));

        if(userRepository.findByEmail(userEntity.getEmail()) != null) {
            throw new EmailException(EmailEnum.INVALID_EMAIL);
        } else if(userRepository.findByDocumentNumber(userEntity.getDocumentNumber()) != null){
            throw new DocumentException(DocumentEnum.INVALID_DOCUMENT);
        } else{
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            return userRepository.save(userEntity);
        }

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

    private String documentNumberFormatting(String documentNumber){
        documentNumber = (documentNumber.replace(".","")
                .replace("-","").replace("/","").replace(" ",""));
        return documentNumber;
    }
}
