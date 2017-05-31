package net.greatstart.services;

import net.greatstart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class MailService {

    private MessageSource messages;
    private JavaMailSender mailSender;
    private Environment env;

    @Autowired
    public MailService(@Qualifier("messageSource")MessageSource messages, JavaMailSender mailSender, Environment env) {
        this.messages = messages;
        this.mailSender = mailSender;
        this.env = env;
    }

    public boolean sendResetTokenEmail(String contextPath, Locale locale, String token, User user) {
        StringBuilder passResetLink = buildPassResetLink(contextPath, token);
        StringBuilder messageBody = new StringBuilder();
        messageBody.append(messages.getMessage("message.resetPassword.body", null, locale))
            .append(passResetLink);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(messageBody.toString(), "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject(messages.getMessage("message.resetPassword.subject", null, locale));
            helper.setFrom(env.getProperty("support.email"));
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    private StringBuilder buildPassResetLink(String contextPath, String token) {
        StringBuilder passResetLink = new StringBuilder();
        passResetLink
            .append("<a href=\"")
            .append(contextPath)
            .append("#!/user/changePassword?token=")
            .append(token)
            .append("\">Reset your password</a>");
        return passResetLink;
    }
}
