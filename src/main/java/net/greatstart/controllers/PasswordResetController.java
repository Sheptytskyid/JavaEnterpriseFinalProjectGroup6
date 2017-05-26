package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.model.User;
import net.greatstart.services.MailService;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.PasswordValidationService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class PasswordResetController {

    public static final String MESSAGE = "message";
    private PasswordEncoder passwordEncoder;
    private MessageSource messages;
    private UserService userService;
    private SecurityService securityService;
    private PasswordValidationService validationService;
    private MailService mailService;

    @Autowired
    public PasswordResetController(@Qualifier("messageSource") MessageSource messages, UserService userService,
                                   SecurityService securityService, PasswordEncoder passwordEncoder,
                                   PasswordValidationService validationService, MailService mailService) {
        this.messages = messages;
        this.userService = userService;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
        this.mailService = mailService;
    }

    @GetMapping(value = "/api/resetPassword")
    @ResponseBody
    public ResponseEntity<String> processEmail(HttpServletRequest request, @RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new ServiceException(messages.getMessage("user.notFound", null, request.getLocale()));
        }
        String passwordResetToken = securityService.createPasswordResetToken(user).getToken();
        String url = request.getHeader("origin");
//        boolean emailSent = mailService.sendResetTokenEmail(url, request.getLocale(), passwordResetToken, user);
        if (true) {
            return new ResponseEntity<>(messages.getMessage("email.sent", null, request.getLocale()),HttpStatus.OK);
        } else {
            throw new ServiceException(messages.getMessage("email.error", null, request.getLocale()));
        }
    }

    @GetMapping(value = "/user/validateToken")
    public ModelAndView validatePassToken(Locale locale, @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(id, token, locale);
        ModelAndView model = new ModelAndView();
        if (result != null) {
            model.addObject(MESSAGE, messages.getMessage("token.error", null, locale) + result);
            model.setViewName("login/login");
        } else {
            model.setViewName("redirect:/user/changePassword");
        }
        return model;
    }

    @PreAuthorize("hasAuthority('CHANGE_PASSWORD_PRIVILEGE')")
    @GetMapping(value = "/user/changePassword")
    public ModelAndView showChangePasswordForm() {
        ModelAndView model = new ModelAndView("user/updatePassword");
        model.addObject("user", new DtoUser());
        return model;
    }

    @PreAuthorize("hasAuthority('CHANGE_PASSWORD_PRIVILEGE')")
    @PostMapping(value = "/user/changePassword")
    public ModelAndView savePassword(Locale locale, @Valid @ModelAttribute("user") DtoUser dtoUser, Errors errors) {
        ModelAndView model = new ModelAndView();
        validationService.validate(dtoUser, errors);
        if (errors.hasErrors()) {
            model.setViewName("user/updatePassword");
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.changeUserPassword(user, passwordEncoder.encode(dtoUser.getPassword()));
            securityService.expireToken(user);
            model.setViewName("login/login");
            model.addObject(MESSAGE, messages.getMessage("message.resetPassword.success", null, locale));
        }
        return model;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<Exception> rulesForException(HttpServletRequest request, ServiceException exception) {
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
