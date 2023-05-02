package br.com.geradordedevs.picpaychallenge.controllers;

import br.com.geradordedevs.picpaychallenge.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import br.com.geradordedevs.picpaychallenge.facades.UserFacade;
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
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;

    private static final String ROUTE = "/user";
    private static final String ROUTE_WITH_ID = "/user/1";
    private static final String WRONG_ROUTE = "/users";

    private final String MOCK_NAME = "Nome";
    private final DocumentTypeEnum MOCK_DOCUMENT_CPF = DocumentTypeEnum.CPF;
    private final DocumentTypeEnum MOCK_DOCUMENT_CNPJ = DocumentTypeEnum.CNPJ;
    private final String MOCK_DOCUMENT_NUMBER = "11111111111111";
    private final String MOCK_EMAIL = "test@test.com";
    private final String MOCK_PASSWORD = "1234";
    private final BigDecimal MOCK_BALANCE = new BigDecimal(1000.00);

    @Test
    public void getUsersShouldReturnOk() throws Exception{
        mockMvc.perform(get(ROUTE)).andExpect(status().isOk());
    }

    @Test
    public void getUsersWhitWrongRoutShouldReturnNotFound() throws Exception{
        mockMvc.perform(get(WRONG_ROUTE)).andExpect(status().isNotFound());
    }

    @Test
    public void findByIdShouldReturnOk() throws Exception{
        mockMvc.perform(get(ROUTE_WITH_ID)).andExpect(status().isOk());
    }

    @Test
    public void findByIdWithWrongRoutShouldReturnNotFound() throws Exception{
        mockMvc.perform(get(WRONG_ROUTE)).andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserShouldReturnOk() throws Exception{
        mockMvc.perform(delete(ROUTE_WITH_ID)).andExpect(status().isOk());
    }

    @Test
    public void deleteUserWithWrongRoutShouldReturnNotFound() throws Exception{
        mockMvc.perform(delete(WRONG_ROUTE)).andExpect(status().isNotFound());
    }

    @Test
    public void saveUserCpfShouldReturnOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(returnObjectUserResquestDTOWithCPF())))
                .andExpect(status().isOk());
    }

    @Test
    public void saveUserCnpjShouldReturnOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithCNPJ())))
                .andExpect(status().isOk());
    }

    @Test
    public void saveUserWithWrongRoutShouldReturnNotFound() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(WRONG_ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithCNPJ())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void saveUserNameNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithNameNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserNameMinShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithNameMinSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserNameMaxShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithNameMaxSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserDocumentTypeNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentTypeNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserDocumentNumberNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentNumberNUll())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserDocumentNumberMinShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentNumberMinSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserDocumentNumberMaxShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentNumberMaxSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserInvalidEmailShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithInvalidEmail())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserEmailNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithEmailNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserPasswordNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithPasswordNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserPasswordMinShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithPasswordMin())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserPasswordMaxShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithPasswordMax())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveUserBalanceNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithBalanceNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserCpfShouldReturnOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithCPF())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserCnpjShouldReturnOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithCNPJ())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserWithWrongRouteShouldReturnNotFound() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(WRONG_ROUTE).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithCNPJ())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserNameNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithNameNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserNameMinShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithNameMinSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserNameMaxShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithNameMaxSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserDocumentTypeNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentTypeNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserDocumentNumberNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentNumberNUll())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserDocumentNumberMinShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentNumberMinSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserDocumentNumberMaxShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithDocumentNumberMaxSize())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserInvalidEmailShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithInvalidEmail())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserEmailNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithEmailNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserPasswordNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithPasswordNull())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserPasswordMinShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithPasswordMin())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserPasswordMaxShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithPasswordMax())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserBalanceNullShouldReturnBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(ROUTE_WITH_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(returnObjectUserResquestDTOWithBalanceNull())))
                .andExpect(status().isBadRequest());
    }

    private UserRequestDTO returnObjectUserResquestDTOWithCPF() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithCNPJ() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CNPJ, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithNameNull() {
        return new UserRequestDTO(null, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithNameMinSize() {
        return new UserRequestDTO("N", MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithNameMaxSize() {
        return new UserRequestDTO("Namenamenamename", MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithDocumentTypeNull() {
        return new UserRequestDTO(MOCK_NAME, null, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithDocumentNumberNUll() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, null, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithDocumentNumberMinSize() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, "123.456.78910", MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithDocumentNumberMaxSize() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, "123.456.789/0001-11", MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithInvalidEmail() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, "test.test", MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithEmailNull() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, null, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithPasswordNull() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, null, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithPasswordMin() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, "123", MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithPasswordMax() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, "123456789", MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithBalanceNull() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, null);
    }
}
