package es.daw.foodexpressmvc.controller;

import es.daw.foodexpressmvc.dto.UserRegisterDTO;
import es.daw.foodexpressmvc.exception.PasswordsDoNotMatchException;
import es.daw.foodexpressmvc.exception.UsernameAlreadyExistsException;
import es.daw.foodexpressmvc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegisterDTO user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "register";
        }

        try{
            userService.register(user);

            return "redirect:/login?registered";
        } catch (UsernameAlreadyExistsException | PasswordsDoNotMatchException ex){
            model.addAttribute("user", new UserRegisterDTO());
            model.addAttribute("errorMessage", ex.getMessage());

            return "register";
        }

    }
}
