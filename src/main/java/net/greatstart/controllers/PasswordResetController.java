package net.greatstart.controllers;

import net.greatstart.dto.DtoUser;
import net.greatstart.model.User;
import net.greatstart.services.MailService;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import net.greatstart.validators.PasswordValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            model.addObject(MESSAGE, messages.getMessage("user.notFound", null, request.getLocale()));
            return model;
        }
        String passwordResetToken = securityService.createPasswordResetTokenForUser(user).getToken();
        String url = request.getHeader("origin");
        boolean emailSent = mailService.sendResetTokenEmail(url, request.getLocale(), passwordResetToken, user);
        if (emailSent) {
            model.addObject(MESSAGE, messages.getMessage("email.sent", null, request.getLocale()));
        } else {
            model.addObject(MESSAGE, messages.getMessage("email.error", null, request.getLocale()));
        }
        return model;
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
            model.addObject(MESSAGE, messages.getMessage("message.resetPassword.success", null, locale));
        }
        return model;
    }


}
