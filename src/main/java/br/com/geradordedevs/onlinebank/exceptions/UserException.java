package br.com.geradordedevs.onlinebank.exceptions;

import br.com.geradordedevs.onlinebank.exceptions.enums.UserEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserException extends OnlinebankException {

    private final UserEnum error;

    public UserException(UserEnum error){
        super(error.getMessage());
        this.error = error;
    }

}
