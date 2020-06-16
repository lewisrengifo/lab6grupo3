package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.repository.PostRepository;

import java.util.Date;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping(value = {"", "/"})
    public String listPost(Model model){
        Date tudei = new Date();
        model.addAttribute("listapost",postRepository.findAll());
        model.addAttribute("hoydia",tudei);
        return "post/list";
    }

    @PostMapping("post/search")
    public String listPostSearch(@RequestParam("buscar") String search,Model model){
        if (search.isEmpty()){
            return "redirect:/";
        }
        model.addAttribute("listapost",postRepository.buscarPorDescripOUser(search));
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
    public String viewPost() {
        return "post/view";
    }

    @PostMapping("/post/comment")
    public String postComment() {
        return "";
    }

    @PostMapping("/post/like")
    public String postLike() {
        return "";
    }
}
