package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.model.User;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.PasswordValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.UUID;

@Controller
public class PasswordResetController {

    private PasswordEncoder passwordEncoder;
    private MessageSource messages;
    private JavaMailSender mailSender;
    private UserService userService;
    private SecurityService securityService;
    private PasswordValidationService validationService;
    private Environment env;

    @Autowired
    public PasswordResetController(@Qualifier("messageSource") MessageSource messages, Environment env, JavaMailSender
        mailSender, UserService userService, SecurityService securityService, PasswordEncoder passwordEncoder,
                                   PasswordValidationService validationService) {
        this.messages = messages;
        this.env = env;
        this.mailSender = mailSender;
        this.userService = userService;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    @GetMapping(value = "/user/resetPassword")
    public ModelAndView showForgotPassForm() {
        ModelAndView model = new ModelAndView("user/forgotPassword");
        model.addObject("user", new DtoUser());
        return model;
    }

    @PostMapping(value = "/user/resetPassword")
    public ModelAndView processEmail(HttpServletRequest request, @Valid @ModelAttribute("user") DtoUser dtoUser, Errors
        errors) {
        ModelAndView model = new ModelAndView("user/forgotPassword");
        if (errors.hasErrors()) {
            return model;
        }
        User user = userService.getUserByEmail(dtoUser.getEmail());
        if (user == null) {
            model.addObject("message", messages.getMessage("user.notFound", null, request.getLocale()));
            return model;
        }
        String passwordResetToken = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, passwordResetToken);
        String url = request.getHeader("origin");
        boolean emailSent = sendResetTokenEmail(url, request.getLocale(), passwordResetToken, user);
        if (emailSent) {
            model.addObject("message", messages.getMessage("email.sent", null, request.getLocale()));
        } else {
            model.addObject("message", messages.getMessage("email.error", null, request.getLocale()));
        }
        return model;
    }

    @GetMapping(value = "/user/validateToken")
    public ModelAndView validatePassToken(Locale locale, @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(id, token);
        ModelAndView model = new ModelAndView();
        if (result != null) {
            model.addObject("message", messages.getMessage("token.error", null, locale) + result);
            model.setViewName("login/login");
        } else {
            model.setViewName("redirect:/user/changePassword");
        }
        return model;
    }

    @GetMapping(value = "/user/changePassword")
    public ModelAndView showChangePasswordForm() {
        ModelAndView model = new ModelAndView("user/updatePassword");
        model.addObject("user", new DtoUser());
        return model;
    }

    @PostMapping(value = "/user/changePassword")
    public ModelAndView savePassword(Locale locale, @Valid @ModelAttribute("user") DtoUser dtoUser, Errors errors) {
        ModelAndView model = new ModelAndView();
        validationService.validate(dtoUser, errors);
        if (errors.hasErrors()) {
            model.setViewName("user/updatePassword");
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.changeUserPassword(user, passwordEncoder.encode(dtoUser.getPassword()));
            model.setViewName("login/login");
            model.addObject("message", messages.getMessage("message.resetPassword.success", null, locale));
        }
        return model;
    }

    private boolean sendResetTokenEmail(String contextPath, Locale locale, String token, User user) {
        StringBuilder messageBody = new StringBuilder();
        messageBody.append(messages.getMessage("message.resetPassword.body", null, locale))
            .append("<a href=\"")
            .append(contextPath)
            .append("/user/validateToken?id=")
            .append(user.getId())
            .append("&token=")
            .append(token)
            .append("\">Reset your password</a>");
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
}
