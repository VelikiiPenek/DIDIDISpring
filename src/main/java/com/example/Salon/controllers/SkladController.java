package com.example.Salon.controllers;


import com.example.Salon.models.Sklad;
import com.example.Salon.models.TipYslygi;
import com.example.Salon.repositories.SkladRepository;
import com.example.Salon.repositories.TipYslygiRepostiory;
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
@RequestMapping("/sklad")
@PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
public class SkladController {


    @Autowired
    private SkladRepository skladRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Sklad> sklads = skladRepository.findAll();
        model.addAttribute("sklad",sklads);
        return "sklad/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Sklad sklad)
    {
        model.addAttribute("sklad",new Sklad());
        return "sklad/add-sklad";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("sklad")
            @Valid Sklad newSklad,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "sklad/add-sklad";


        skladRepository.save(newSklad);
        return "redirect:/sklad/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("sklad") String Sklad,
            Model model)
    {
        List<Sklad> newsList = skladRepository.findByadresSklada(Sklad);
        model.addAttribute("sklad",newsList);
        return "sklad/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Sklad> sklad = skladRepository.findById(id);
        ArrayList<Sklad> skladArrayList = new ArrayList<>();
        sklad.ifPresent(skladArrayList::add);
        model.addAttribute("sklad",skladArrayList);
        return "sklad/info-sklad";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!skladRepository.existsById(id))
        {
            return "redirect:/sklad/";
        }
        Optional<Sklad> sklad = skladRepository.findById(id);
        ArrayList<Sklad> skladArrayList = new ArrayList<>();
        sklad.ifPresent(skladArrayList::add);
        model.addAttribute("sklad",skladArrayList.get(0));
        return "sklad/edit-sklad";
    }


    @PostMapping("/edit/{id}")
    public String editCars(
            @PathVariable("id") Long id,
            @ModelAttribute("sklad") @Valid Sklad newSklad,
            BindingResult bindingResult,
            Model model)
    {
        if(!skladRepository.existsById(id))
        {
            return "redirect:/sklad";
        }
        if(bindingResult.hasErrors())
            return "sklad/edit-sklad";

        newSklad.setId(id);

        skladRepository.save(newSklad);
        return "redirect:/sklad/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Sklad sklad = skladRepository.findById(id).orElseThrow();
        skladRepository.delete(sklad);
        return "redirect:/sklad/";
    }
}
