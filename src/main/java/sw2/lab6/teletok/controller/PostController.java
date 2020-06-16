package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.repository.PostCommentRepository;
import sw2.lab6.teletok.repository.PostLikeRepository;
import sw2.lab6.teletok.repository.PostRepository;
import sw2.lab6.teletok.repository.UserRepository;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostLikeRepository postLikeRepository;
    @Autowired
    PostCommentRepository postCommentRepository;

    @GetMapping(value = {"", "/"})
    public String listPost(){
        return "post/list";
    }

    @GetMapping("/post/new")
    public String newPost(){
        return "post/new";
    }

    @PostMapping("/post/save")
    public String savePost() {
        return "redirect:/";
    }

    @GetMapping("/post/file/{media_url}")
    public String getFile() {
        return "";
    }

    @GetMapping("/post/{id}")
    public String viewPost(Model model, @ModelAttribute Post post, @PathVariable("id") int post_id) {

        model.addAttribute("Descripcion",postRepository.findDescripcionbyId(post.getId()));
        model.addAttribute("likes", postLikeRepository.cantidaddelikes(post.getId()));
        model.addAttribute("usuario", userRepository.findAutorPost(post.getId()));
        model.addAttribute("post", postRepository.findById(post_id));
        model.addAttribute("listacomments", postCommentRepository.comendsbyid(post.getId()));
        return "post/view";
    }

    @PostMapping("/post/comment")
    public String postComment(@ModelAttribute("PostComment") PostComment postComment, Model model) {

        postCommentRepository.save(postComment);
        return "redirect:/post/{id}";
    }

    @PostMapping("/post/like")
    public String postLike() {
        return "";
    }
}
