package net.greatstart.controllers;

import net.greatstart.model.GenericResponse;
import net.greatstart.model.User;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;

@Controller
public class PasswordResetController {

    private MessageSource messages;
    private Environment env;
    private JavaMailSender mailSender;
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public PasswordResetController(MessageSource messages, Environment env, JavaMailSender mailSender, UserService
        userService, SecurityService securityService) {
        this.messages = messages;
        this.env = env;
        this.mailSender = mailSender;
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(value = "/user/resetPassword")
    @ResponseBody
    public GenericResponse resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("Email not found");
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        String url = request.getRequestURL().toString();
        mailSender.send(constructResetTokenEmail(url, request.getLocale(), token, user));
        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
    }

    private SimpleMailMessage constructResetTokenEmail(
        String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail(message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Reset Password");
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
    public String showChangePasswordPage(Locale locale, Model model,
                                         @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(id, token);
        if (result != null) {
            model.addAttribute("message",
                messages.getMessage("auth.message." + result, null, locale));
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }

    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(Locale locale,
                                        @Valid PasswordDto passwordDto) {
        User user =
            (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse(
            messages.getMessage("message.resetPasswordSuc", null, locale));
    }
}
