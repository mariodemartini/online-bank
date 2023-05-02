package br.com.geradordedevs.onlinebank.facades.impl;

import br.com.geradordedevs.onlinebank.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.onlinebank.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.onlinebank.entities.UserEntity;
import br.com.geradordedevs.onlinebank.enums.DocumentTypeEnum;
import br.com.geradordedevs.onlinebank.mappers.UserMapper;
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
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserFacadeImplTest {

    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private final Long MOCK_ID = 1L;
    private final String MOCK_NAME = "Name";
    private final DocumentTypeEnum MOCK_DOCUMENT_CPF = DocumentTypeEnum.CPF;
    private final DocumentTypeEnum MOCK_DOCUMENT_CNPJ = DocumentTypeEnum.CNPJ;
    private final String MOCK_CNPJ_NUMBER = "11.111.111/0001-11";
    private final String MOCK_DOCUMENT_NUMBER = "111.111.111-56";
    private final String MOCK_EMAIL = "test@test.com";
    private final String MOCK_PASSWORD ="123456789";
    private final BigDecimal MOCK_BALANCE = new BigDecimal(1000.00);

    @Before
    public void setupMock() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(userService.getUsers()).thenReturn(returnListUserEntity());
        when(userService.saveUser(returnObjectUserEntity())).thenReturn(returnObjectUserEntity());
        when(userService.saveUser(returnObjectUserEntityWithCNPJ())).thenReturn(returnObjectUserEntityWithCNPJ());
        when(userService.updateUser(MOCK_ID, returnObjectUserEntity())).thenReturn(returnObjectUserEntity());
        when(userService.findById(MOCK_ID)).thenReturn(returnObjectUserEntity());

        when(userMapper.toListDTO(returnListUserEntity())).thenReturn(returnListUserResponseDTO());
        when(userMapper.toDTO(returnObjectUserEntity())).thenReturn(returnObjectUserResponseDTOWithCPF());
        when(userMapper.toDTO(returnObjectUserEntityWithCNPJ())).thenReturn(returnObjectUserResponseDTOWithCNPJ());
        when(userMapper.toEntity(returnObjectUserResquestDTOWithCPF())).thenReturn(returnObjectUserEntity());
        when(userMapper.toEntity(returnObjectUserResquestDTOWithCNPJ())).thenReturn(returnObjectUserEntityWithCNPJ());
    }

    @Test
    public void getUsersShouldReturnOk() throws Exception{
        assertEquals(returnListUserResponseDTO(), userFacade.getUsers());
    }

    @Test
    public void saveUsersWithCpfShouldReturnOk() throws Exception{
        assertEquals(returnObjectUserResponseDTOWithCPF(), userFacade.saveUser(returnObjectUserResquestDTOWithCPF()));
    }

    @Test
    public void saveUsersWithCnpjShouldReturnOk() throws Exception{
        assertEquals(returnObjectUserResponseDTOWithCNPJ(), userFacade.saveUser(returnObjectUserResquestDTOWithCNPJ()));
    }

    @Test
    public void updateUserShoulReturnOk() throws Exception{
        assertEquals(returnObjectUserResponseDTOWithCPF(), userFacade.updateUser(MOCK_ID, returnObjectUserResquestDTOWithCPF()));
    }

    @Test
    public void findByIdShoulReturnOk() throws Exception{
        assertEquals(returnObjectUserResponseDTOWithCPF(), userFacade.findById(MOCK_ID));
    }

    @Test
    public void deleteUserShouldReturnOk() throws Exception{
        userFacade.deleteUser(MOCK_ID);
        verify(userService, timeout(1)).deleteUser(MOCK_ID);
    }

    private List<UserResponseDTO> returnListUserResponseDTO() {
        List<UserResponseDTO> list = new ArrayList<>();
        list.add(returnObjectUserResponseDTOWithCPF());

        return list;
    }

    private UserResponseDTO returnObjectUserResponseDTOWithCPF() {
        return new UserResponseDTO(MOCK_ID, MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_BALANCE);
    }

    private List<UserEntity> returnListUserEntity() {
        List<UserEntity> list = new ArrayList<>();
        list.add(returnObjectUserEntity());

        return list;
    }

    private UserEntity returnObjectUserEntity() {
        return new UserEntity(MOCK_ID, MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithCPF() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CPF, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD,MOCK_BALANCE);
    }

    private UserEntity returnObjectUserEntityWithCNPJ() {
        return new UserEntity(MOCK_ID, MOCK_NAME, MOCK_DOCUMENT_CNPJ, MOCK_CNPJ_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserRequestDTO returnObjectUserResquestDTOWithCNPJ() {
        return new UserRequestDTO(MOCK_NAME, MOCK_DOCUMENT_CNPJ, MOCK_CNPJ_NUMBER, MOCK_EMAIL, MOCK_PASSWORD,MOCK_BALANCE);
    }

    private UserResponseDTO returnObjectUserResponseDTOWithCNPJ() {
        return new UserResponseDTO(MOCK_ID, MOCK_NAME, MOCK_DOCUMENT_CNPJ, MOCK_CNPJ_NUMBER, MOCK_EMAIL, MOCK_BALANCE);
    }
}
