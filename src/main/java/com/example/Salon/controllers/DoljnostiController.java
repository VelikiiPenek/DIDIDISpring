package com.example.Salon.controllers;


import com.example.Salon.models.Doljnosti;
import com.example.Salon.models.Sklad;
import com.example.Salon.models.TipYslygi;
import com.example.Salon.repositories.DoljnostiRepository;
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
@RequestMapping("/doljnosti")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class DoljnostiController {


    @Autowired
    private DoljnostiRepository doljnostiRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Doljnosti> doljnostis = doljnostiRepository.findAll();
        model.addAttribute("doljnosti",doljnostis);
        return "doljnosti/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Doljnosti doljnosti)
    {
        model.addAttribute("doljnosti",new Doljnosti());
        return "doljnosti/add-doljnosti";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("doljnosti")
            @Valid Doljnosti newDoljnosti,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "doljnosti/add-doljnosti";


        doljnostiRepository.save(newDoljnosti);
        return "redirect:/doljnosti/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("doljnosti") String doljnosti,
            Model model)
    {
        List<Doljnosti> newsList = doljnostiRepository.findBynamePost(doljnosti);
        model.addAttribute("doljnosti",newsList);
        return "doljnosti/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Doljnosti> doljnosti = doljnostiRepository.findById(id);
        ArrayList<Doljnosti> doljnostiArrayList = new ArrayList<>();
        doljnosti.ifPresent(doljnostiArrayList::add);
        model.addAttribute("doljnosti",doljnostiArrayList);
        return "doljnosti/info-doljnosti";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!doljnostiRepository.existsById(id))
        {
            return "redirect:/doljnosti/";
        }
        Optional<Doljnosti> doljnosti = doljnostiRepository.findById(id);
        ArrayList<Doljnosti> doljnostiArrayList = new ArrayList<>();
        doljnosti.ifPresent(doljnostiArrayList::add);
        model.addAttribute("doljnosti",doljnostiArrayList.get(0));
        return "doljnosti/edit-doljnosti";
    }


    @PostMapping("/edit/{id}")
    public String editCars(
            @PathVariable("id") Long id,
            @ModelAttribute("doljnosti") @Valid Doljnosti newDoljnosti,
            BindingResult bindingResult,
            Model model)
    {
        if(!doljnostiRepository.existsById(id))
        {
            return "redirect:/doljnosti";
        }
        if(bindingResult.hasErrors())
            return "doljnosti/edit-doljnosti";

        newDoljnosti.setId(id);

        doljnostiRepository.save(newDoljnosti);
        return "redirect:/doljnosti/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Doljnosti doljnosti = doljnostiRepository.findById(id).orElseThrow();
        doljnostiRepository.delete(doljnosti);
        return "redirect:/doljnosti/";
    }
}
