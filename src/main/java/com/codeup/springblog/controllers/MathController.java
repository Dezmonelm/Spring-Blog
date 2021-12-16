package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @RequestMapping(path = "/add/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String addTwoNumbers(@PathVariable int num1, @PathVariable int num2){
        return num1 + " PLUS(+) " + num2 + " EQUALS(=) " + (num1 + num2) + "!";
    }

    @RequestMapping(path = "/subtract/{num1}/from/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtractTwoNumbers(@PathVariable int num1, @PathVariable int num2){
        return num1 + " MINUS(-) " + num2 + " EQUALS(=) " + (num1 - num2) + "!";
    }

    @RequestMapping(path = "/multiply/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String multiplyTwoNumbers(@PathVariable int num1, @PathVariable int num2){
        return num1 + " MULTIPLIED BY(*) " + num2 + " EQUALS(=) " + (num1 * num2) + "!";
    }

    @RequestMapping(path = "/divide/{num1}/by/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String dividedTwoNumbers(@PathVariable int num1,@PathVariable int num2){
        return num1 + " DIVIDED BY(/) " + num2 + " EQUALS(=) " + (num1 / num2) + "!";
    }

}
