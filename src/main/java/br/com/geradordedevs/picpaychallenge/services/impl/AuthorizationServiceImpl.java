package br.com.geradordedevs.picpaychallenge.services.impl;

import br.com.geradordedevs.picpaychallenge.clients.AuthorizationClient;
import br.com.geradordedevs.picpaychallenge.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.picpaychallenge.services.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationClient authorizationClient;

    @Override
    public AuthorizationResponseDTO authorization(){
        log.info("authorizing transaction");
        return authorizationClient.authorization();
    }
}
