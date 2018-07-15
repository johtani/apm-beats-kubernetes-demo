package info.johtani.apmdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }

    @RequestMapping(value = "/soslow", method = RequestMethod.GET)
    public String soSlow(Model model) {
        return "soslow";
    }

}
