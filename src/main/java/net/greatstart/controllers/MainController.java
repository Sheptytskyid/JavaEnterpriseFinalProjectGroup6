package net.greatstart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public ModelAndView frontPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
//--------------------------------------------------------------
    @RequestMapping("/view/{page}")
    public ModelAndView viewHandler(@PathVariable("page") final ModelAndView page) {
        return page;
    }
}
