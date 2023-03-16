package com.example.Salon.controllers;


import com.example.Salon.models.*;
import com.example.Salon.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/klient")
@PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
public class KlientController {


    @Autowired
    private KlientRepository klientRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Klient> klients = klientRepository.findAll();
        model.addAttribute("klient",klients);
        return "klient/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Klient klient)
    {
        model.addAttribute("klient",new Klient());
        return "klient/add-klient";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("klient")
            @Valid Klient newKlient,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("klient",newKlient);
            return "klient/add-klient";
        }

        klientRepository.save(newKlient);
        return "redirect:/klient/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("klient") String Klient,
            Model model)
    {
        List<Klient> newsList = klientRepository.findBysurename(Klient);
        model.addAttribute("klient",newsList);
        return "klient/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Klient> klient = klientRepository.findById(id);
        ArrayList<Klient> klientArrayList = new ArrayList<>();
        klient.ifPresent(klientArrayList::add);
        model.addAttribute("klient",klientArrayList);
        return "klient/info-klient";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!klientRepository.existsById(id))
        {
            return "redirect:/klient/";
        }
        Optional<Klient> klients = klientRepository.findById(id);
        ArrayList<Klient> klientArrayList = new ArrayList<>();
        klients.ifPresent(klientArrayList::add);
        model.addAttribute("klient",klientArrayList.get(0));
        return "klient/edit-klient";
    }


    @PostMapping("/edit/{id}")
    public String editNabori(
            @PathVariable("id") Long id,
            @ModelAttribute("klient") @Valid Klient newKlient,
            BindingResult bindingResult,
            Model model)
    {
        if(!klientRepository.existsById(id))
        {
            return "redirect:/klient";
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("klient",newKlient);
            return "klient/edit-klient";
        }

        newKlient.setId(id);

        klientRepository.save(newKlient);
        return "redirect:/klient/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Klient klient = klientRepository.findById(id).orElseThrow();
        klientRepository.delete(klient);
        return "redirect:/klient/";
    }
}
