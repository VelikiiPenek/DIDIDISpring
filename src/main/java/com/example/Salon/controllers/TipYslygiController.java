package com.example.Salon.controllers;


import com.example.Salon.models.TipYslygi;
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
@RequestMapping("/tipyslygi")
@PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
public class TipYslygiController {


    @Autowired
    private TipYslygiRepostiory tipYslygiRepostiory;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<TipYslygi> tipYslygis = tipYslygiRepostiory.findAll();
        model.addAttribute("tipyslygi",tipYslygis);
        return "tipyslygi/index";
    }

    @GetMapping("/add")
    public String addView(Model model, TipYslygi tipYslygi)
    {
        model.addAttribute("tipyslygi",new TipYslygi());
        return "tipyslygi/add-tipyslygi";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("tipyslygi")
            @Valid TipYslygi newTipYslygi,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "tipyslygi/add-tipyslygi";


        tipYslygiRepostiory.save(newTipYslygi);
        return "redirect:/tipyslygi/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("tipyslygi") String TipYslygi,
            Model model)
    {
        List<TipYslygi> newsList = tipYslygiRepostiory.findBytipYslygi(TipYslygi);
        model.addAttribute("tipyslygi",newsList);
        return "tipyslygi/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<TipYslygi> tipYslygi = tipYslygiRepostiory.findById(id);
        ArrayList<TipYslygi> tipYslygiMarketArrayList = new ArrayList<>();
        tipYslygi.ifPresent(tipYslygiMarketArrayList::add);
        model.addAttribute("tipyslygi",tipYslygiMarketArrayList);
        return "tipyslygi/info-tipyslygi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!tipYslygiRepostiory.existsById(id))
        {
            return "redirect:/tipyslygi/";
        }
        Optional<TipYslygi> tipYslygi = tipYslygiRepostiory.findById(id);
        ArrayList<TipYslygi> tipYslygiMarketArrayList = new ArrayList<>();
        tipYslygi.ifPresent(tipYslygiMarketArrayList::add);
        model.addAttribute("tipyslygi",tipYslygiMarketArrayList.get(0));
        return "tipyslygi/edit-tipyslygi";
    }


    @PostMapping("/edit/{id}")
    public String editCars(
            @PathVariable("id") Long id,
            @ModelAttribute("tipyslygi") @Valid TipYslygi newTipYslygi,
            BindingResult bindingResult,
            Model model)
    {
        if(!tipYslygiRepostiory.existsById(id))
        {
            return "redirect:/tipyslygi";
        }
        if(bindingResult.hasErrors())
            return "tipyslygi/edit-tipyslygi";

        newTipYslygi.setId(id);

        tipYslygiRepostiory.save(newTipYslygi);
        return "redirect:/tipyslygi/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        TipYslygi tipYslygi = tipYslygiRepostiory.findById(id).orElseThrow();
        tipYslygiRepostiory.delete(tipYslygi);
        return "redirect:/tipyslygi/";
    }
}
