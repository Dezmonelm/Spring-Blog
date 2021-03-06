package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String viewAllPosts(Model viewModel) {

        List<Post> posts = postDao.findAll();

        viewModel.addAttribute("posts", posts);

        return "posts/index";
    }

    @GetMapping("/posts/show")
    public String viewOnePost(Model viewModel) {
        Post newPost = new Post("Hello there!", "Wut up doe?");

        viewModel.addAttribute("post", newPost);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model){

    model.addAttribute("post", new Post());

        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String insertPost(@ModelAttribute Post post) {
        // below for creating a user for the first time
//        User dez = new User();
//        dez.setUsername("dez");
//        dez.setPassword("dez");
//        dez.setEmail("dez@send.com");
//        userDao.save(dez);
        post.setPostCreator(userDao.getById(2L));

        String emailSubject = post.getPostCreator().getUsername() + "your post has been created ";

        String emailBody = "Good Job " + post.getBody();

        emailService.prepareAndSend(post, emailSubject, emailBody);
postDao.save(post);

//        Post newPost1 = new Post("How would you like...", "to go get some pizza?");
//        Post newPost2 = new Post("South Park the tv show...", "is very interesting!");
//
//        newPost1.setPostCreator(postOwner);
////        newPost2.setPostCreator(dez);
//
//        postDao.save(newPost1);
//        postDao.save(newPost2);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        Post editPost = postDao.getById(id);

        System.out.println("editPost.getTitle() = " + editPost.getTitle());
        System.out.println("editPost.getBody() = " + editPost.getBody());

        model.addAttribute("postToEdit", editPost);
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String saveEditPost(@RequestParam(name = "postBody") String postBody, @RequestParam(name = "postTitle") String postTitle, @RequestParam(name = "postId") long id) {
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
    public String deletePost(@PathVariable long id) {

        long deletePostId = id;
        postDao.deleteById(deletePostId);

        return "redirect:/posts";
    }


}
