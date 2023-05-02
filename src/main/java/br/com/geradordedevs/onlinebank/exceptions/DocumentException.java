package br.com.geradordedevs.onlinebank.exceptions;

import br.com.geradordedevs.onlinebank.exceptions.enums.DocumentEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class DocumentException extends OnlinebankException {

    private final DocumentEnum error;

    public DocumentException(DocumentEnum error){
        super(error.getMessage());
        this.error = error;
    }
}
