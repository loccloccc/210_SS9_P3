package org.example.bai3.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class HomeController {
    @PostMapping("/change-theme")
    public String changeTheme(
            @RequestParam("theme") String theme,
            HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("theme", theme);

        cookie.setMaxAge(60 * 60 * 24 * 30); 
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping()
    public String home(
            @CookieValue(value = "theme", required = false, defaultValue = "light") String theme,
            Model model
    ) {
        model.addAttribute("theme", theme);
        return "home";
    }
}
