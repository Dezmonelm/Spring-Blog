package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {

    @GetMapping("/rollDice")
    public String showJoinForm() {
        return "roll-dice";
    }

    @PostMapping("/rollDice/{number}")
    public String result(@PathVariable int number, Model model) {

        int random = (int) (Math.random() * 6 + 1);

        model.addAttribute("number", number);
        model.addAttribute("diceNumber", random);
        return "diceResults";
    }

}
