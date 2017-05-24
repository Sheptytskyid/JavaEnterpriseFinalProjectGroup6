package net.greatstart.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.Principal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {
    @Mock
    private Principal principal;

    @InjectMocks
    private UserRestController controller;

    @Test
    public void user() throws Exception {
        assertEquals(principal, controller.user(principal));

    }

}