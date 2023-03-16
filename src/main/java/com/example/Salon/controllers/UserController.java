package com.example.Salon.controllers;


import com.example.Salon.models.Role;
import com.example.Salon.models.User;
import com.example.Salon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String all_users(Model model)
    {
        model.addAttribute("users",
                userRepository.findAll());
        return "users/info-users";
    }

    @GetMapping("/edit/{id}")
    public String user_role(
            @PathVariable("id")
            Long id,
            Model model)
    {
        Optional<User> user_raw = userRepository.findById(id);
        ArrayList<User> userArrayList = new ArrayList<>();

        user_raw.ifPresent(userArrayList::add);

        model.addAttribute("one_user",userArrayList);
        model.addAttribute("roles", Role.values());
        return "users/edit-users";
    }

    @PostMapping
    public String edit_role
            (@RequestParam("userId") User user,
             @RequestParam("username") String username,
             @RequestParam(name = "roles[]",required = false)
             String[] roles)
    {
        user.setUsername(username);
        user.getRoles().clear();
        if(roles!=null)
        {
            for (String role_name:
                 roles) {
                user.getRoles().add(Role.valueOf(role_name));
            }
        }
        userRepository.save(user);
        return "redirect:/admin";
    }
}
