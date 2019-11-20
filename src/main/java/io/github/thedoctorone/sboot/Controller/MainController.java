package io.github.thedoctorone.sboot.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController implements ErrorController {

    private static final String PATH = "/error";
    @Value("${app.company}")
    private String company;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("company", company);
        return "index";
    }

    @GetMapping(PATH)
    @ResponseBody
    public String error(Model model) {
        return "Error mate.";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}