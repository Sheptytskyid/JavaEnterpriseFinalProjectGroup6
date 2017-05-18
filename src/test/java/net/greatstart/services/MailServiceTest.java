package net.greatstart.services;

import net.greatstart.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest {

    private static final String LOCALHOST = "http://localhost:8080/";
    private static final String TOKEN_VALUE = "1";
    private static final String MESSAGE = "message";
    private static final String EMAIL = "test@test.com";
    private static final String SUPPORT_EMAIL = "GreatStartSupport@meta.ua";
    @Mock
    private MessageSource messages;
    @Mock
    private JavaMailSender mailSender;
    @Mock
    private Environment env;
    @Mock
    private MimeMessage mimeMessage;
    @InjectMocks
    private MailService mailService;
    private Locale locale;
    private User user;

    @Before
    public void setUp() {
        locale = new Locale("en");
        user = new User();
        user.setEmail(EMAIL);
    }

    @Test(timeout = 2000)
    public void sendResetTokenEmail() throws Exception {
        when(messages.getMessage("message.resetPassword.body", null, locale)).thenReturn(MESSAGE);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(env.getProperty("support.email")).thenReturn(SUPPORT_EMAIL);
        when(messages.getMessage("message.resetPassword.subject", null, locale)).thenReturn(MESSAGE);
        mailService.sendResetTokenEmail(LOCALHOST, locale, TOKEN_VALUE, user);
        verify(mailSender, times(1)).send(mimeMessage);
    }
}
