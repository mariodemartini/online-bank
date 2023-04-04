package br.com.geradordedevs.picpaychallenge.facades.impl;
import br.com.geradordedevs.picpaychallenge.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.AuthorizationResponseDTO;
import br.com.geradordedevs.picpaychallenge.dtos.responses.TransactionResponseDTO;
import br.com.geradordedevs.picpaychallenge.entities.TransactionEntity;
import br.com.geradordedevs.picpaychallenge.entities.UserEntity;
import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import br.com.geradordedevs.picpaychallenge.facades.TransactionFacade;
import br.com.geradordedevs.picpaychallenge.mappers.TransactionMapper;
import br.com.geradordedevs.picpaychallenge.mappers.UserMapper;
import br.com.geradordedevs.picpaychallenge.services.AuthorizationService;
import br.com.geradordedevs.picpaychallenge.services.TransactionService;
import br.com.geradordedevs.picpaychallenge.services.UserService;
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
import static org.mockito.Mockito.*;

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

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transactionService.findById(MOCK_ID)).thenReturn(returnObjectTransactionEntity());
        when(transactionService.getTransactions()).thenReturn(returnListTransactionEntity());
        when(transactionMapper.toDTO(returnObjectTransactionEntity())).thenReturn(returnObjectTransactionResponseDTO());
        when(transactionMapper.toListDTO(returnListTransactionEntity())).thenReturn(returnListTransactionResponseDTO());
    }

    @Test
    public void findByIdShloudReturnOk() throws Exception{
        assertEquals(returnObjectTransactionResponseDTO(), transactionFacade.findById(MOCK_ID));
    }

    @Test
    public void getTransactionsShouldReturnOK() throws Exception{
        assertEquals(returnListTransactionResponseDTO(), transactionFacade.getTransactions());
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

}
