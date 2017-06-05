package net.greatstart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * A simple controller to return a home page. Isn't used with REST.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/indexjsp")
    public ModelAndView frontPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
