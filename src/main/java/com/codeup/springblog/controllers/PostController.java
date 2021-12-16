package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String viewAllPosts(){
        return "LOOK AT AAAALLLL THOSE POSTS!";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewOnePost(@PathVariable int id){
        return "This is one Post " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "view the form for creating a post!";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insertPost(){
        return "create a new post!";
    }

}
