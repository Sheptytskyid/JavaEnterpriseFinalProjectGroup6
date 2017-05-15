package net.greatstart.controllers;

import net.greatstart.dto.DtoEmail;
import net.greatstart.dto.DtoPassword;
import net.greatstart.model.GenericResponse;
import net.greatstart.model.User;
import net.greatstart.services.SecurityService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.UUID;

@Controller
public class PasswordResetController {

    private PasswordEncoder passwordEncoder;
//    private MessageSource messages;
    private JavaMailSender mailSender;
    private UserService userService;
    private SecurityService securityService;
    private Environment env;

    @Autowired
    public PasswordResetController(/*MessageSource messages,*/ Environment env, JavaMailSender mailSender, UserService
        userService, SecurityService securityService, PasswordEncoder passwordEncoder) {
//        this.messages = messages;
        this.env = env;
        this.mailSender = mailSender;
        this.userService = userService;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/user/resetPassword")
    public ModelAndView showForgotPassForm() {
        ModelAndView model = new ModelAndView("user/forgotPassword");
        model.addObject("email", new DtoEmail());
        return model;
    }

    @PostMapping(value = "/user/resetPassword")
    public ModelAndView resetPassword(HttpServletRequest request, @Valid @ModelAttribute("email") DtoEmail userEmail, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("user/forgotPassword");
        }
        User user = userService.getUserByEmail(userEmail.getEmail());
        if (user == null) {
            throw new RuntimeException("Email not found"); // replace with error message
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        String url = request.getRequestURL().toString();
        mailSender.send(constructResetTokenEmail(url, request.getLocale(), token, user));
//        return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
        return new ModelAndView("index");
    }

    private SimpleMailMessage constructResetTokenEmail(
        String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
//        String message = messages.getMessage("message.resetPassword", null, locale);
        String message = "message.resetPassword";
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

    @GetMapping(value = "/user/changePassword")
    public String showChangePasswordPage(Locale locale, Model model,
                                         @RequestParam("id") long id, @RequestParam("token") String token) {
        String result = securityService.validatePasswordResetToken(id, token);
        if (result != null) {
//            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
            model.addAttribute("message", "auth.message");
            return "redirect:/login?lang=" + locale.getLanguage();
        }
        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
    }

    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse savePassword(Locale locale,
                                        @Valid DtoPassword dtoPassword) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.changeUserPassword(user, passwordEncoder.encode(dtoPassword.getPassword()));
//        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
        return new GenericResponse("message.resetPasswordSuc");
    }
}
