package es.daw.foodexpressmvc.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "dashboard";
    }
}
