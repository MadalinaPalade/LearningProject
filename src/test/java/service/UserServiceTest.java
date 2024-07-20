package service;

import com.learning.entity.UserInfo;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    //@InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(repository.findByName(anyString())).thenReturn(getUserInfo());
        userService = new UserService(repository, encoder);

    }
    @Test
    public void loadUserByUsername_successfully() {
        //when
        UserDetails user = userService.loadUserByUsername("Madalina");
        //then
        assertTrue(user.getUsername().equals("Madalina"));
    }

    private Optional<UserInfo> getUserInfo(){
       return Optional.of(new UserInfo(1, "Madalina", "Madalina", "palde.madalina@gmail.com", "password123", "USER_ROLE"));
    }
}
