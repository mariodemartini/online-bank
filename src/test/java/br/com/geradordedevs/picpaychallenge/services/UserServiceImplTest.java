package br.com.geradordedevs.picpaychallenge.services;

import br.com.geradordedevs.picpaychallenge.entities.UserEntity;
import br.com.geradordedevs.picpaychallenge.enums.DocumentTypeEnum;
import br.com.geradordedevs.picpaychallenge.repositories.UserRepository;
import br.com.geradordedevs.picpaychallenge.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private final Long MOCK_ID = 1L;
    private final String MOCK_NAME = "User";
    private final DocumentTypeEnum MOCK_DOCUMENT_TYPE = DocumentTypeEnum.CPF;
    private final String MOCK_DOCUMENT_NUMBER = "123.123.123-11";
    private final String MOCK_DOCUMENT_NUMBER_NULL = null;
    private final String MOCK_EMAIL = "test@test.com";
    private final String MOCK_EMAIL_NULL = null;
    private final String MOCK_PASSWORD = "abcde";
    private final String MOCK_PASSWORD_CRYPT = "&7L9l";
    private final BigDecimal MOCK_BALANCE = new BigDecimal(100.00);

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(userRepository.findAll()).thenReturn(returnListUserEntity());
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(returnObjectUserEntity()));
        when(userRepository.save(returnObjectUserEntity())).thenReturn(returnObjectUserEntity());
    }

    @Test
    public void getUsersShouldReturnOk() throws Exception{
        assertEquals(returnListUserEntity(), userRepository.findAll());
    }

    @Test
    public void findByIdShouldReturnOk() throws Exception{
        assertEquals(returnObjectUserEntity(), userService.findById(MOCK_ID));
    }

    @Test
    public void deleteUserShouldReturnOk() throws Exception{
        userService.deleteUser(MOCK_ID);
        verify(userRepository, timeout(1)).deleteById(MOCK_ID);
    }

    @Test
    public void updateUserShouldReturnOk() throws Exception{
        assertEquals(returnObjectUserEntity(), userService.updateUser(MOCK_ID, returnObjectUserEntity()));
    }

    private List<UserEntity> returnListUserEntity() {
        List<UserEntity> list = new ArrayList<>();
        list.add(returnObjectUserEntity());

        return list;
    }

    private UserEntity returnObjectUserEntity() {
        return new UserEntity(MOCK_ID, MOCK_NAME, MOCK_DOCUMENT_TYPE, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD, MOCK_BALANCE);
    }

    private UserEntity returnObjectUserEntityPasswordCrypt() {
        return new UserEntity(MOCK_NAME, MOCK_DOCUMENT_TYPE, MOCK_DOCUMENT_NUMBER, MOCK_EMAIL, MOCK_PASSWORD_CRYPT, MOCK_BALANCE);
    }

    private UserEntity returnObjectUserEntityPasswordCryptEmailNull() {
        return new UserEntity(MOCK_NAME, MOCK_DOCUMENT_TYPE, MOCK_DOCUMENT_NUMBER_NULL, MOCK_EMAIL_NULL, MOCK_PASSWORD_CRYPT, MOCK_BALANCE);
    }

}
