package br.com.geradordedevs.onlinebank.controllers;

import br.com.geradordedevs.onlinebank.dtos.requests.TransactionRequestDTO;
import br.com.geradordedevs.onlinebank.facades.TransactionFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionFacade transactionFacade;

    private static final String ROUTE = "/transaction";
    private static final String ROUTE_WITH_ID = "/transaction/1";
    private static final String WRONG_ROUTE = "/transactions";

    private static final BigDecimal MOCK_VALUE = new BigDecimal(1000.00);
    private static final Long MOCK_PAYER = 1L;
    private static final Long MOCK_PAYEE = 2L;

    @Test
    public void listTransactionsShouldReturnOk() throws Exception{
        mockMvc.perform(get(ROUTE)).andExpect(status().isOk());
    }

    @Test
    public void listTransactionShouldReturnNotFound() throws Exception{
        mockMvc.perform(get(WRONG_ROUTE)).andExpect(status().isNotFound());
    }

    @Test
    public void findByIdShouldReturnOk() throws Exception{
        mockMvc.perform(get(ROUTE_WITH_ID)).andExpect(status().isOk());
    }

    @Test
    public void findByIdShouldReturnNotFound() throws Exception{
        mockMvc.perform(get(WRONG_ROUTE)).andExpect(status().isNotFound());
    }

    @Test
    public void transactionShouldRetunOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionResquestDTO()))).andExpect(status().isOk());
    }

    @Test
    public void transactionWithWrongRouteShouldRetunNotFound() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(WRONG_ROUTE).contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionResquestDTO()))).andExpect(status().isNotFound());
    }

    @Test
    public void transactionWhitoutValueShouldRetunBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnTransactionResquestDTOWithoutValue())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transactionWhitoutPayerShouldRetunBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnTransactionResquestDTOWithoutPayer())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transactionWhitoutPayeeShouldRetunBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnTransactionResquestDTOWithoutPayee())))
                .andExpect(status().isBadRequest());
    }

    private TransactionRequestDTO returnTransactionResquestDTO() {
        return new TransactionRequestDTO(MOCK_VALUE, MOCK_PAYER, MOCK_PAYEE);
    }

    private TransactionRequestDTO returnTransactionResquestDTOWithoutValue() {
        return new TransactionRequestDTO(null, MOCK_PAYER, MOCK_PAYEE);
    }

    private TransactionRequestDTO returnTransactionResquestDTOWithoutPayer() {
        return new TransactionRequestDTO(MOCK_VALUE, 0L, MOCK_PAYEE);
    }

    private TransactionRequestDTO returnTransactionResquestDTOWithoutPayee() {
        return new TransactionRequestDTO(MOCK_VALUE, MOCK_PAYER, 0L);
    }
}
