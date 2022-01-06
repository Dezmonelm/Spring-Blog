package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String viewAllPosts(Model viewModel){

        List<Post> posts = postDao.findAll();

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
//        User dez
//        userDao.save(userPost1);
//        userDao.save(userPost2);

        return "redirect: /posts";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insertPost(){

        Post newPost1 = new Post("How would you like...", "to go get some pizza?");
        Post newPost2 = new Post("South Park the tv show...", "is very interesting!");

        postDao.save(newPost1);
        postDao.save(newPost2);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model){
        Post editPost = postDao.getById(id);
        System.out.println("editPost.getTitile() = " + editPost.getTitle());
        System.out.println("editPost.getBody() = " + editPost.getBody());

        model.addAttribute("postToEdit", editPost);
                return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String saveEditPost(@RequestParam(name="postBody") String postBody,@RequestParam(name="postTitle") String postTitle, @RequestParam(name="postId") long id){
        System.out.println("id = " + id);
        System.out.println("postTitle = " + postTitle);
        System.out.println("postBody = " + postBody);

        Post postToEdit = postDao.getById(id);
        postToEdit.setBody(postBody);
        postToEdit.setTitle(postTitle);

        System.out.println("NEW postBody = " + postToEdit.getBody());
        System.out.println("NEW postTitle0 = " + postToEdit.getTitle());

        postDao.save(postToEdit);

        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id){
        long deletePostId = id;
        postDao.deleteById(deletePostId);

        return "redirect:/posts";
    }




}
