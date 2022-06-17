package com.sk.started.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostsWebController {
    private final PostService postService;

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts/posts-save";
    }

    @GetMapping("/posts/list")
    public String postsList(Model model) {
        model.addAttribute("posts", postService.findAllDesc());
        return "posts/posts-list";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);
        return "posts/posts-update";
    }


}
