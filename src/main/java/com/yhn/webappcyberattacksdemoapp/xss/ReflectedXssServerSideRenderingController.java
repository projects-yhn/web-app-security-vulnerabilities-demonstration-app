package com.yhn.webappcyberattacksdemoapp.xss;

import com.yhn.webappcyberattacksdemoapp.xss.model.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/xss/reflected/server-side")
public class ReflectedXssServerSideRenderingController {

    private final ReflectedXssService reflectedXssService;

    @GetMapping("/search-posts")
    public String search(@RequestParam(value = "search", required = false) String searchValue, Model model) {
        if (StringUtils.hasText(searchValue)) {
            List<PostDto> posts = reflectedXssService.searchPostByTitle(searchValue);
            if (!posts.isEmpty()) {
                model.addAttribute("posts", posts);
                return "search-posts-with-results";
            } else {
                model.addAttribute("errorText", "Not found any posts with title:");
                model.addAttribute("errorTextProperty", searchValue);
                return "not-found";
            }
        } else {
            return "search-posts";
        }

    }
}
