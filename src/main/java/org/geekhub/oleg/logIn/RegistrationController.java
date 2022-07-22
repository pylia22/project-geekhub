package org.geekhub.oleg.logIn;

import org.geekhub.oleg.user.model.User;
import org.geekhub.oleg.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public RegistrationController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String submitRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "/registration";
        }
        userServiceImpl.createUser(user);

        return "redirect:/login";
    }
}
