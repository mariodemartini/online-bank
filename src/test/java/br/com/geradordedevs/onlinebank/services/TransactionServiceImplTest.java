package br.com.geradordedevs.onlinebank.services;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.repositories.TransactionRepository;
import br.com.geradordedevs.onlinebank.services.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private final Long MOCK_ID = 1L;
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
        when(transactionRepository.save(returnObjectTransactionEntity())).thenReturn(returnObjectTransactionEntity());
        when(transactionRepository.findById(MOCK_ID)).thenReturn(Optional.of(returnObjectTransactionEntity()));
        when(transactionRepository.findAll()).thenReturn(returnListTransactionEntity());
    }

    @Test
    public void saveTransactionShouldReturnOk() throws Exception{
        assertEquals(returnObjectTransactionEntity(), transactionService.saveTransaction(returnObjectTransactionEntity()));
    }

    @Test
    public void findByIdShouldReturnOK() throws Exception{
        assertEquals(returnObjectTransactionEntity(), transactionService.findById(MOCK_ID));
    }

    @Test
    public void getTransactionsShouldReturnOk() throws Exception{
        assertEquals(returnListTransactionEntity(), transactionService.getTransactions());
    }

    private TransactionEntity returnObjectTransactionEntity() {
        return new TransactionEntity(MOCK_ID, MOCK_VALUE, MOCK_PAYER, MOCK_PAYEE);
    }

    private List<TransactionEntity> returnListTransactionEntity(){
        List<TransactionEntity> list = new ArrayList<>();
        list.add(returnObjectTransactionEntity());

        return list;
    }
}
