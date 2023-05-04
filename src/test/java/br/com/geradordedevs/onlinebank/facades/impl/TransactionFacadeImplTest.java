package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.mappers.TransactionMapper;
import br.com.geradordedevs.onlinebank.mappers.UserMapper;
import br.com.geradordedevs.onlinebank.services.AuthorizationService;
import br.com.geradordedevs.onlinebank.services.LoginService;
import br.com.geradordedevs.onlinebank.services.TransactionService;
import br.com.geradordedevs.onlinebank.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransactionFacadeImplTest {

    @InjectMocks
    private TransactionFacadeImpl transactionFacade;

    @Mock
    private TransactionService transactionService;

    @Mock
    private UserService userService;

    @Mock
    private LoginService loginService;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private UserMapper userMapper;

    private final Long MOCK_ID = 1L;
    private final String MOCK_MESSAGE = "Autorizado";
    private final BigDecimal MOCK_VALUE = new BigDecimal(1000.00);
    private final String MOCK_PAYER = "Payer";
    private final String MOCK_PAYEE = "Payee";
    private final Long MOCK_ID_PAYER = 1L;
    private final Long MOCK_ID_PAYEE = 2L;
    private final String MOCK_NAME = "Name";
    private final DocumentTypeEnum MOCK_DOCUMENT_CPF = DocumentTypeEnum.CPF;
    private final DocumentTypeEnum MOCK_DOCUMENT_CNPJ = DocumentTypeEnum.CNPJ;
    private final String MOCK_CNPJ_NUMBER = "11.111.111/0001-11";
    private final String MOCK_DOCUMENT_NUMBER = "111.111.111-56";
    private final String MOCK_EMAIL = "test@test.com";
    private final String MOCK_PASSWORD ="123456789";
    private final BigDecimal MOCK_BALANCE = new BigDecimal(1000.00);
    private static final String MOCK_TOKEN = "GOFMGFOMGOFMGOFMGOFMGOFMGFOMGOFMGOFMGOFM";

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transactionService.findById(MOCK_ID)).thenReturn(returnObjectTransactionEntity());
        when(transactionService.getTransactions()).thenReturn(returnListTransactionEntity());
        when(transactionService.saveTransaction(returnObjectTransactionEntity())).thenReturn(returnObjectTransactionEntity());

        when(userService.findById(MOCK_ID_PAYER)).thenReturn(returnObjectUserEntityPayer());
        when(userService.findById(MOCK_ID_PAYEE)).thenReturn(returnObjectUserEntityPayee());
        when(userService.updateUser(MOCK_ID_PAYER, returnObjectUserEntityPayer())).thenReturn(returnObjectUserEntityPayer());
        when(userService.updateUser(MOCK_ID_PAYEE, returnObjectUserEntityPayer())).thenReturn(returnObjectUserEntityPayee());

        when(transactionMapper.toDTO(returnObjectTransactionEntity())).thenReturn(returnObjectTransactionResponseDTO());
        when(transactionMapper.toListDTO(returnListTransactionEntity())).thenReturn(returnListTransactionResponseDTO());
        when(transactionMapper.toDTO(returnObjectTransactionEntity())).thenReturn(returnObjectTransactionResponseDTO());

        when(userMapper.toDTO(returnObjectUserEntityPayer())).thenReturn(returnObjectUserResponseDTOPayer());
        when(userMapper.toDTO(returnObjectUserEntityPayee())).thenReturn(returnObjectUserResponseDTOPayee());
    }

    @Test
    public void findByIdShloudReturnOk() throws Exception{
        loginService.validate(MOCK_TOKEN);
        assertEquals(returnObjectTransactionResponseDTO(), transactionFacade.findById(MOCK_ID, MOCK_TOKEN));
    }

    @Test
    public void getTransactionsShouldReturnOK() throws Exception{
        loginService.validate(MOCK_TOKEN);
        assertEquals(returnListTransactionResponseDTO(), transactionFacade.getTransactions(MOCK_TOKEN));
    }

    @Test
    public void transactionShoulReturnOk() throws Exception{
        loginService.validate(MOCK_TOKEN);
        assertEquals(returnAuthorizationResponseDTO(), transactionFacade.transaction(returnObjectTransactionRequestDTO(), MOCK_TOKEN));
    }

    private AuthorizationResponseDTO returnAuthorizationResponseDTO() {
            return authorizationService.authorization();
    }

    private TransactionRequestDTO returnObjectTransactionRequestDTO() {
        return new TransactionRequestDTO(MOCK_VALUE, MOCK_ID_PAYER, MOCK_ID_PAYEE);
    }

    private TransactionResponseDTO returnObjectTransactionResponseDTO(){
        return new TransactionResponseDTO(MOCK_ID, MOCK_VALUE, MOCK_PAYER, MOCK_PAYEE);
    }

    private TransactionEntity returnObjectTransactionEntity(){
        return new TransactionEntity(MOCK_ID, MOCK_VALUE, MOCK_PAYER, MOCK_PAYEE);
    }

    private List<TransactionResponseDTO> returnListTransactionResponseDTO() {
        List<TransactionResponseDTO> list = new ArrayList<>();
        list.add(returnObjectTransactionResponseDTO());

        return list;
    }

    private List<TransactionEntity> returnListTransactionEntity() {
        List<TransactionEntity> list = new ArrayList<>();
        list.add(returnObjectTransactionEntity());

        return list;
    }

    private UserEntity returnObjectUserEntityPayer(){
        return new UserEntity(MOCK_ID_PAYER, MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserEntity returnObjectUserEntityPayee(){
        return new UserEntity(MOCK_ID_PAYEE, MOCK_NAME, MOCK_DOCUMENT_CNPJ, MOCK_CNPJ_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserResponseDTO returnObjectUserResponseDTOPayer() {
        return new UserResponseDTO(MOCK_ID_PAYER, MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_BALANCE);
    }

    private UserResponseDTO returnObjectUserResponseDTOPayee() {
        return new UserResponseDTO(MOCK_ID_PAYEE, MOCK_NAME, MOCK_DOCUMENT_CNPJ, MOCK_CNPJ_NUMBER, MOCK_EMAIL, MOCK_BALANCE);
    }

}
