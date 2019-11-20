package io.github.thedoctorone.sboot.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.thedoctorone.sboot.Repository.UserRepo;

@Controller
public class MainController implements ErrorController {

    private static final String PATH = "/error";
    @Autowired
    private UserRepo userRepo;
    @Value("${app.company}")
    private String company;

    @GetMapping("/index")
    public String urlIndex(Model model, HttpSession session) { 
        return index(model, session);
    }
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if(username == null) {
            model.addAttribute("company", "Welcome to " + company);
            return "index";
        } else {
            model.addAttribute("user", username);
            return "index2";
        }
        
    }
    @PostMapping("/*")
    public String postIndex(@RequestParam(name = "username", required = true) String username ,
    @RequestParam(name = "password", required = true) String password , 
    @RequestParam(name = "login", required = false) String buttonLogin,
    @RequestParam(name = "register", required = false) String buttonRegister,
    Model model , HttpServletRequest req) {
        username = username.trim().toLowerCase();
        if(username.equals("") || password.trim().equals("")) { //Returns wrong at bottom.

        } else if(buttonLogin != null) {
            if(userRepo.loginCheck(username, password)){
                req.getSession().setAttribute("username", username);
                model.addAttribute("user", username);
                return "index2";
            }
        } else if (buttonRegister != null) {
            if(userRepo.register(username, password)) {
                req.getSession().setAttribute("username", username);
                model.addAttribute("user", username);
                return "index2";
            }
        } 
        model.addAttribute("company", "wrong");
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