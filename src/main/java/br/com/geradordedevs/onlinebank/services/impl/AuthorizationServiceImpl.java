package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.clients.AuthorizationClient;
import br.com.geradordedevs.onlinebank.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.onlinebank.services.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationClient authorizationClient;

    @Override
    public AuthorizationResponseDTO authorization() {
        log.info("authorizing transaction");
        return authorizationClient.authorization();
    }

}
