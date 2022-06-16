package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "user/index";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult){

        if(bindingResult.hasErrors())  return "user/new";

        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "user/new";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "user/view";

        userService.updateUser(user);
        return "redirect:/" + user.getId();
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable int id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "user/view";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "redirect:/";
    }



}
