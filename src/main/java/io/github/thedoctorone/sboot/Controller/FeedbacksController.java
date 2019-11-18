package io.github.thedoctorone.sboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.thedoctorone.sboot.Service.FeedbackDataHolder;
import io.github.thedoctorone.sboot.Model.*;

@Controller
public class FeedbacksController {

    private FeedbackDataHolder fdh = FeedbackDataHolder.feedHolder;

    @GetMapping("/feedbacks*")
    public String getFeedbacks(Model model) {
        String temp = "\n";
        for(Feedback feedback : fdh.getFeedbacks()) {
            temp += feedback + " *-br-* ";
        }
        //temp = temp.substring(0, temp.length() - 5);
        model.addAttribute("feedbacks", temp);
        return "feedbacks";
    }

}