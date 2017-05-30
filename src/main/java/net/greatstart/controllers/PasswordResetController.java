package net.greatstart.controllers;

import net.greatstart.model.User;
import net.greatstart.services.MailService;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
public class PasswordResetController {

    private PasswordEncoder passwordEncoder;
    private MessageSource messages;
    private UserService userService;
    private SecurityService securityService;
    private MailService mailService;

    @Autowired
    public PasswordResetController(@Qualifier("messageSource") MessageSource messages, UserService userService,
                                   SecurityService securityService, PasswordEncoder passwordEncoder,
                                   MailService mailService) {
        this.messages = messages;
        this.userService = userService;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @GetMapping(value = "/user/resetPassword")
    public ResponseEntity<String[]> processEmail(HttpServletRequest request, @RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new ServiceException(messages.getMessage("user.notFound", null, request.getLocale()));
        }
        String passwordResetToken = securityService.createPasswordResetToken(user).getToken();
        String url = request.getHeader("referer");
        boolean emailSent = mailService.sendResetTokenEmail(url, request.getLocale(), passwordResetToken, user);
        if (emailSent) {
            return new ResponseEntity<>(new String[]{messages.getMessage("email.sent", null, request.getLocale())},
                HttpStatus.OK);
        } else {
            throw new ServiceException(messages.getMessage("email.error", null, request.getLocale()));
        }
    }

    @GetMapping(value = "/user/validateToken")
    public ModelAndView validatePassToken(Locale locale, @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(id, token, locale);
        ModelAndView model = new ModelAndView();
        if (result != null) {
            model.setViewName("redirect:/");
            //TODO: implement displaying errors, maybe in some generic error page
        } else {
            model.setViewName("redirect:/#!/user/changePassword");
        }
        return model;
    }

    @PreAuthorize("hasAuthority('CHANGE_PASSWORD_PRIVILEGE')")
    @PostMapping(value = "/user/resetPassword")
    public HttpStatus savePassword(Locale locale, @RequestBody String password) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, passwordEncoder.encode(password));
        securityService.expireToken(user);
        return HttpStatus.OK;
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Exception> rulesForException(HttpServletRequest request, ServiceException exception) {
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
