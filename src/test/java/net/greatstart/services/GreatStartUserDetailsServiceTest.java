package net.greatstart.services;

import net.greatstart.model.Role;
import net.greatstart.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GreatStartUserDetailsServiceTest {

    private static final String EMAIL = "test@test.com";
    private static final String TEST_PASS = "testPass";
    private static final String ROLE = "user";
    @Mock
    private UserService userService;
    @InjectMocks
    private GreatStartUserDetailsService userDetailsService;

    @Test(timeout = 2000, expected = UsernameNotFoundException.class)
    public void throwExceptionWhenLoadUserByNonExistingUsername() throws Exception {
        userDetailsService.loadUserByUsername(EMAIL);
    }

    @Test(timeout = 2000)
    public void loadUserByUsername() throws Exception {
        net.greatstart.model.User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ROLE));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(ROLE));
        user.setEmail(EMAIL);
        user.setRoles(roles);
        user.setPassword(TEST_PASS);
        when(userService.getUserByEmail(EMAIL)).thenReturn(user);
        org.springframework.security.core.userdetails.UserDetails result = userDetailsService.loadUserByUsername(EMAIL);
        assertEquals(TEST_PASS,result.getPassword());
        assertEquals(EMAIL,result.getUsername());
        assertEquals(authorities,result.getAuthorities());
    }
}
