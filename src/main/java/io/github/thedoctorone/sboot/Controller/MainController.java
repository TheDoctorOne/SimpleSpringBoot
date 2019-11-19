package io.github.thedoctorone.sboot.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController implements ErrorController {

    private static final String PATH = "/error";

    @GetMapping("/")
    public String index(@RequestParam(name = "membername", required= false, defaultValue = "World")String name, Model model) {
        model.addAttribute("membername", name);
        return "hello";
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