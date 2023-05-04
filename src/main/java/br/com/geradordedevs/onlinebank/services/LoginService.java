package br.com.geradordedevs.onlinebank.services;

public interface LoginService {

    String generateToken(String email);

    void validate(String token);
}
