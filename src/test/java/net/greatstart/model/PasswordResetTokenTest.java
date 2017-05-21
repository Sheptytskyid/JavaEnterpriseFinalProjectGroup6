package net.greatstart.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PasswordResetTokenTest {

    private static final String TOKEN_VALUE = "1";

    @Test(timeout = 2000)
    public void constructorShouldSetFields() {
        User user = new User();
        String token = TOKEN_VALUE;
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, LocalDateTime.now().plusHours(6), false);
        assertFalse(passwordResetToken.isUsed());
        assertEquals(TOKEN_VALUE, passwordResetToken.getToken());
        assertEquals(user, passwordResetToken.getUser());
        assertTrue(passwordResetToken.getExpiryDate().isAfter(LocalDateTime.now()));
    }
}