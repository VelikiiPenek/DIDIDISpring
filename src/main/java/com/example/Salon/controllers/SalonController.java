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
@RequestMapping("/salon")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class SalonController {


    @Autowired
    private SalonRepository salonRepository;
    @Autowired
    private AdresSalonaRepository adresSalonaRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Salon> salons = salonRepository.findAll();
        model.addAttribute("salon",salons);
        return "salon/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Salon salon)
    {
        model.addAttribute("salon",new Salon());
        Iterable<AdresSalona> adresSalonas = adresSalonaRepository.findAll();
        model.addAttribute("adressalona", adresSalonas);
        return "salon/add-salon";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("salon")
            @Valid Salon newSalon,
            BindingResult bindingResult,
            Model model,@RequestParam String adressalona)
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("salon",newSalon);
            Iterable<AdresSalona> adresSalonas = adresSalonaRepository.findAll();
            model.addAttribute("adressalona",adresSalonas);
            return "salon/add-salon";
        }
        AdresSalona adresSalona = adresSalonaRepository.findByadresSalona(adressalona).get(0);
        newSalon.setAdresSalona(adresSalona);

        salonRepository.save(newSalon);
        return "redirect:/salon/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("salon") String Salon,
            Model model)
    {
        List<Salon> newsList = salonRepository.findBynaimenovanieSalona(Salon);
        model.addAttribute("salon",newsList);
        return "salon/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Salon> salon = salonRepository.findById(id);
        ArrayList<Salon> salonArrayList = new ArrayList<>();
        salon.ifPresent(salonArrayList::add);
        model.addAttribute("salon",salonArrayList);
        return "salon/info-salon";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!salonRepository.existsById(id))
        {
            return "redirect:/salon/";
        }
        Optional<Salon> salon = salonRepository.findById(id);
        ArrayList<Salon> salonArrayList = new ArrayList<>();
        salon.ifPresent(salonArrayList::add);
        model.addAttribute("salon",salonArrayList.get(0));
        Iterable<AdresSalona> adresSalonas = adresSalonaRepository.findAll();
        model.addAttribute("adressalona",adresSalonas);
        return "salon/edit-salon";
    }


    @PostMapping("/edit/{id}")
    public String editNabori(
            @PathVariable("id") Long id,
            @ModelAttribute("salon") @Valid Salon newSalon,
            BindingResult bindingResult,
            Model model,@RequestParam String adressalona)
    {
        if(!salonRepository.existsById(id))
        {
            return "redirect:/salon";
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("salon",newSalon);
            Iterable<AdresSalona> adresSalonas = adresSalonaRepository.findAll();
            model.addAttribute("adressalona",adresSalonas);
            return "salon/edit-salon";
        }
        AdresSalona adresSalona = adresSalonaRepository.findByadresSalona(adressalona).get(0);
        newSalon.setAdresSalona(adresSalona);

        newSalon.setId(id);

        salonRepository.save(newSalon);
        return "redirect:/salon/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Salon salon = salonRepository.findById(id).orElseThrow();
        salonRepository.delete(salon);
        return "redirect:/salon/";
    }
}
