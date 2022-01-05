package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDoa;

    public PostController(PostRepository postDao){
        this.postDoa = postDao;
    }

    @GetMapping("/posts")
    public String viewAllPosts(Model viewModel){

        Post newPost1 = new Post("How would you like...", "to go get some pizza?");
        Post newPost2 = new Post("South Park the tv show...", "is very interesting!");

        ArrayList<Post> posts = new ArrayList<>();
        posts.add(newPost1);
        posts.add(newPost2);

        viewModel.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/show")
    public String viewOnePost(Model viewModel){
        Post newPost = new Post("Hello there!", "Wut up doe?");
        viewModel.addAttribute("post", newPost);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "view the form for creating a post!";
    }

//    @GetMapping("posts/edit/{id}")

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id){
        long deletePostId = id;
        postDoa.deleteById(deletePostId);

        return "redirect: /posts";
    }


    @PostMapping("/posts/create")
    @ResponseBody
    public String insertPost(){
        return "create a new post!";
    }

}
