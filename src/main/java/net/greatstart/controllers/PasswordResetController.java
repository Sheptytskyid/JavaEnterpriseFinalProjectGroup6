/**
 * A REST controller to provide the "retrieve lost password" functionality.
 */
package net.greatstart.controllers;

import net.greatstart.model.User;
import net.greatstart.services.MailService;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


@RestController
public class PasswordResetController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private SecurityService securityService;
    private MailService mailService;

    @Autowired
    public PasswordResetController(UserService userService, SecurityService securityService, PasswordEncoder passwordEncoder,
                                   MailService mailService) {
        this.userService = userService;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @GetMapping(value = "/user/resetPassword")
    public ResponseEntity<String> processEmail(HttpServletRequest request, @RequestParam String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String passwordResetToken = securityService.createPasswordResetToken(user).getToken();
        boolean emailSent = mailService.sendResetTokenEmail(request, request.getLocale(), passwordResetToken, user);
        if (emailSent) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/user/resetPassword")
    public HttpStatus savePassword(Locale locale, @RequestBody String password, @RequestParam String token) {
        String validationResult = securityService.validatePasswordResetToken(token, locale);
        if (validationResult != null) {
            return HttpStatus.BAD_REQUEST;
        } else {
            User user = securityService.getUserByToken(token);
            userService.changeUserPassword(user, passwordEncoder.encode(password));
            securityService.expireToken(user);
        }
        return HttpStatus.OK;
    }
}
