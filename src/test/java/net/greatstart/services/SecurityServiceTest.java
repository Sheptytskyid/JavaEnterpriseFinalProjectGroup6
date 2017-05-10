package net.greatstart.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest {

    private static final String NAME = "name";
    private static final String PASS = "pass";

    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetails userDetails;
    @InjectMocks
    private SecurityService securityService;

    @Test
    public void autoLogin() throws Exception {
        Set authorities = new HashSet();
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, PASS, authorities);
        when(userDetailsService.loadUserByUsername(NAME)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(authorities);
        when(authenticationToken.isAuthenticated()).thenReturn(true);
        securityService.autoLogin(NAME, PASS);
        verify(userDetailsService, times(1)).loadUserByUsername(NAME);
        verify(authenticationManager, times(1)).authenticate(authenticationToken);
    }
}
