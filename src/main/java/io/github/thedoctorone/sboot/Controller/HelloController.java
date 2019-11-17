package io.github.thedoctorone.sboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello*")
    public String sayHey(@RequestParam(name = "membername", defaultValue = "World", required = false)String membername, Model model) {
        model.addAttribute("membername", membername);
        return "hello";
    }
}