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
@RequestMapping("/sotrydniki")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class SotrydnikiController {


    @Autowired
    private SotrydnikiRepository sotrydnikiRepository;
    @Autowired
    private DoljnostiRepository doljnostiRepository;
    @Autowired
    private SalonRepository salonRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Sotrydniki> sotrydnikis = sotrydnikiRepository.findAll();
        model.addAttribute("sotrydniki",sotrydnikis);
        return "sotrydniki/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Sotrydniki sotrydniki)
    {
        model.addAttribute("sotrydniki",new Sotrydniki());
        Iterable<Doljnosti> doljnostis = doljnostiRepository.findAll();
        model.addAttribute("doljnosti",doljnostis);
        Iterable<Salon> salons = salonRepository.findAll();
        model.addAttribute("salon",salons);
        return "sotrydniki/add-sotrydniki";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("sotrydniki")
            @Valid Sotrydniki newSotrydniki,
            BindingResult bindingResult,
            Model model,
            @RequestParam String doljnosti,
            @RequestParam String salon)
    {
//        if(bindingResult.hasErrors()) {
//            model.addAttribute("sotrydniki",newSotrydniki);
//            Iterable<Doljnosti> doljnostis = doljnostiRepository.findAll();
//            model.addAttribute("doljnosti",doljnostis);
//            Iterable<Salon> salons = salonRepository.findAll();
//            model.addAttribute("salon",salons);
//            return "sotrydniki/add-sotrydniki";
//        }
        Doljnosti doljnosti1 = doljnostiRepository.findBynamePost(doljnosti).get(0);
        newSotrydniki.setDoljnost(doljnosti1);
        Salon salon1 = salonRepository.findBynaimenovanieSalona(salon).get(0);
        newSotrydniki.setSalon(salon1);

        sotrydnikiRepository.save(newSotrydniki);
        return "redirect:/sotrydniki/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("sotrydniki") String Sotrydniki,
            Model model)
    {
        List<Sotrydniki> newsList = sotrydnikiRepository.findBysurename(Sotrydniki);
        model.addAttribute("sotrydniki",newsList);
        return "sotrydniki/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Sotrydniki> sotrydniki = sotrydnikiRepository.findById(id);
        ArrayList<Sotrydniki> sotrydnikiArrayList = new ArrayList<>();
        sotrydniki.ifPresent(sotrydnikiArrayList::add);
        model.addAttribute("sotrydniki",sotrydnikiArrayList);
        return "sotrydniki/info-sotrydniki";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!sotrydnikiRepository.existsById(id))
        {
            return "redirect:/sotrydniki/";
        }
        Optional<Sotrydniki> sotrydniki = sotrydnikiRepository.findById(id);
        ArrayList<Sotrydniki> sotrydnikiArrayList = new ArrayList<>();
        sotrydniki.ifPresent(sotrydnikiArrayList::add);
        model.addAttribute("sotrydniki",sotrydnikiArrayList.get(0));
        Iterable<Doljnosti> doljnostis = doljnostiRepository.findAll();
        model.addAttribute("doljnosti",doljnostis);
        Iterable<Salon> salons = salonRepository.findAll();
        model.addAttribute("salon",salons);
        return "sotrydniki/edit-sotrydniki";
    }


    @PostMapping("/edit/{id}")
    public String editCars(
            @PathVariable("id") Long id,
            @ModelAttribute("sotrydniki") @Valid Sotrydniki newSotrydniki,
            BindingResult bindingResult,
            Model model,@RequestParam String doljnosti,@RequestParam String salon)
    {
        if(!sotrydnikiRepository.existsById(id))
        {
            return "redirect:/sotrydniki";
        }
//        if(bindingResult.hasErrors()) {
//            model.addAttribute("sotrydniki",newSotrydniki);
//            Iterable<Doljnosti> doljnostis = doljnostiRepository.findAll();
//            model.addAttribute("doljnosti",doljnostis);
//            Iterable<Salon> salons = salonRepository.findAll();
//            model.addAttribute("salon",salons);
//            return "sotrydniki/edit-sotrydniki";
//        }
        Doljnosti doljnostis = doljnostiRepository.findBynamePost(doljnosti).get(0);
        newSotrydniki.setDoljnost(doljnostis);
        Salon salons = salonRepository.findBynaimenovanieSalona(salon).get(0);
        newSotrydniki.setSalon(salons);

        newSotrydniki.setId(id);

        sotrydnikiRepository.save(newSotrydniki);
        return "redirect:/sotrydniki/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Sotrydniki sotrydniki = sotrydnikiRepository.findById(id).orElseThrow();
        sotrydnikiRepository.delete(sotrydniki);
        return "redirect:/sotrydniki/";
    }
}
