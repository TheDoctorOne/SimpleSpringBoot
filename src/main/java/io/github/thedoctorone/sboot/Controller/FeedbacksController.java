package io.github.thedoctorone.sboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.thedoctorone.sboot.Model.*;
import io.github.thedoctorone.sboot.Repository.FeedbackRepo;

@Controller
public class FeedbacksController {
    @Autowired
    private FeedbackRepo feedbackRepo;

    @GetMapping("/feedbacks*")
    public String getFeedbacks(Model model) {
        String temp = "\n";
        for(Feedback feedback : feedbackRepo.getFeedbacks()) {
            temp += feedback + " *-br-* ";
        }
        model.addAttribute("feedbacks", temp);
        return "feedbacks";
    }
}