package sw2.lab6.teletok.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.entity.StorageService;
import sw2.lab6.teletok.entity.User;
import sw2.lab6.teletok.repository.PostRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@Controller
public class PostController {
    @Autowired
    private StorageService storageService;
    @Autowired
    PostRepository postRepository;

    @GetMapping(value = {"", "/"})
    public String listPost(Model model){
        model.addAttribute("listaPost",postRepository.findAll());
        return "post/list";
    }

    @GetMapping("/post/new")
    public String newPost(@ModelAttribute("post") Post post){

        return "post/new";
    }

    @PostMapping("/post/save")
    public String savePost(@RequestParam("media_url")MultipartFile file, @ModelAttribute("post") @Valid Post post, BindingResult bindingResult,
                           Model model, RedirectAttributes attr,HttpSession session) {

        HashMap<String,String> map = storageService.store(file);
        if(map.get("estado").equals("exito")){
            post.setMediaUrl(map.get("fileName"));
            if (bindingResult.hasErrors()){
                return "post/new";
            }
            else{
                if (post.getId() == 0) {
                    attr.addFlashAttribute("msg", "Post Creado exitosamente");
                }
                post.setCreationDate(new Date());
                User user=(User) session.getAttribute("user");
                post.setUser(user);
                postRepository.save(post);
                model.addAttribute("listaPost",postRepository.findAll());
                return "redirect:/";
            }
        }
        else {
            model.addAttribute("msgg",map.get("msg"));
            return "post/new";
        }
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
