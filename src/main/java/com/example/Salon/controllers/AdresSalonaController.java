package com.example.Salon.controllers;


import com.example.Salon.models.AdresSalona;
import com.example.Salon.models.Doljnosti;
import com.example.Salon.models.Sklad;
import com.example.Salon.models.TipYslygi;
import com.example.Salon.repositories.AdresSalonaRepository;
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
@RequestMapping("/adressalona")
@PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
public class AdresSalonaController {


    @Autowired
    private AdresSalonaRepository adresSalonaRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<AdresSalona> adresSalonas = adresSalonaRepository.findAll();
        model.addAttribute("adressalona",adresSalonas);
        return "adressalona/index";
    }

    @GetMapping("/add")
    public String addView(Model model, AdresSalona adresSalona)
    {
        model.addAttribute("adressalona",new AdresSalona());
        return "adressalona/add-adressalona";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("adressalona")
            @Valid AdresSalona newAdressalona,
            BindingResult bindingResult,
            Model model)
    {
        if(bindingResult.hasErrors())
            return "adressalona/add-adressalona";


        adresSalonaRepository.save(newAdressalona);
        return "redirect:/adressalona/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("adressalona") String adressalona,
            Model model)
    {
        List<AdresSalona> newsList = adresSalonaRepository.findByadresSalona(adressalona);
        model.addAttribute("adressalona",newsList);
        return "adressalona/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<AdresSalona> adresSalona = adresSalonaRepository.findById(id);
        ArrayList<AdresSalona> adresSalonaArrayList = new ArrayList<>();
        adresSalona.ifPresent(adresSalonaArrayList::add);
        model.addAttribute("adressalona",adresSalonaArrayList);
        return "adressalona/info-adressalona";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!adresSalonaRepository.existsById(id))
        {
            return "redirect:/adressalona/";
        }
        Optional<AdresSalona> adresSalona = adresSalonaRepository.findById(id);
        ArrayList<AdresSalona> adresSalonaArrayList = new ArrayList<>();
        adresSalona.ifPresent(adresSalonaArrayList::add);
        model.addAttribute("adressalona",adresSalonaArrayList.get(0));
        return "adressalona/edit-adressalona";
    }


    @PostMapping("/edit/{id}")
    public String editCars(
            @PathVariable("id") Long id,
            @ModelAttribute("adressalona") @Valid AdresSalona newAdressalona,
            BindingResult bindingResult,
            Model model)
    {
        if(!adresSalonaRepository.existsById(id))
        {
            return "redirect:/adressalona";
        }
        if(bindingResult.hasErrors())
            return "adressalona/edit-adressalona";

        newAdressalona.setId(id);

        adresSalonaRepository.save(newAdressalona);
        return "redirect:/adressalona/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        AdresSalona adresSalona = adresSalonaRepository.findById(id).orElseThrow();
        adresSalonaRepository.delete(adresSalona);
        return "redirect:/adressalona/";
    }
}
