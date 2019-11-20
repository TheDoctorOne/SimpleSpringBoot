package io.github.thedoctorone.sboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.thedoctorone.sboot.Model.Feedback;
import io.github.thedoctorone.sboot.Repository.FeedbackRepo;

@Controller
public class HelloController {
    @Autowired
    private FeedbackRepo feedbackRepo;

    @GetMapping("/hello*")
    public String sayHey(@RequestParam(name = "membername", defaultValue = "World", required = false)String membername, Model model) {
        model.addAttribute("membername", membername);
        return "hello";
    }

    @PostMapping("/hello*")
    public String getFeedback(@RequestParam(name = "name", required = true) String name, 
        @RequestParam(name = "mail", required = true) String mail, 
        @RequestParam(name = "feedback", required = true) String feedback, Model model) {
            model.addAttribute("membername", name + "! We recieved your feedback!");
            int id = feedbackRepo.getFeedbacks().size();
            feedbackRepo.addFeedback(new Feedback(id, name, mail, feedback));
        return "hello";
    }
}