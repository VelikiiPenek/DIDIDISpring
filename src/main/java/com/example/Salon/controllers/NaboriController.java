package com.example.Salon.controllers;


import com.example.Salon.models.Nabori;
import com.example.Salon.models.Sklad;
import com.example.Salon.models.TipYslygi;
import com.example.Salon.repositories.NaboriRepository;
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
@RequestMapping("/nabori")
@PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
public class NaboriController {


    @Autowired
    private NaboriRepository naboriRepository;
    @Autowired
    private SkladRepository skladRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Nabori> naboris = naboriRepository.findAll();
        model.addAttribute("nabori",naboris);
        return "nabori/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Nabori nabori)
    {
        model.addAttribute("nabori",new Nabori());
        Iterable<Sklad> sklads = skladRepository.findAll();
        model.addAttribute("sklad",sklads);
        return "nabori/add-nabori";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("nabori")
            @Valid Nabori newNabori,
            BindingResult bindingResult,
            Model model,@RequestParam String adres)
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("nabori",newNabori);
            Iterable<Sklad> sklads = skladRepository.findAll();
            model.addAttribute("sklad",sklads);
            return "nabori/add-nabori";
        }
        Sklad sklads = skladRepository.findByadresSklada(adres).get(0);
        newNabori.setAdressSklada(sklads);

        naboriRepository.save(newNabori);
        return "redirect:/nabori/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("nabori") String Nabori,
            Model model)
    {
        List<Nabori> newsList = naboriRepository.findBynameKit(Nabori);
        model.addAttribute("nabori",newsList);
        return "nabori/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Nabori> nabori = naboriRepository.findById(id);
        ArrayList<Nabori> naboriArrayList = new ArrayList<>();
        nabori.ifPresent(naboriArrayList::add);
        model.addAttribute("nabori",naboriArrayList);
        return "nabori/info-nabori";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!naboriRepository.existsById(id))
        {
            return "redirect:/nabori/";
        }
        Optional<Nabori> nabori = naboriRepository.findById(id);
        ArrayList<Nabori> naboriArrayList = new ArrayList<>();
        nabori.ifPresent(naboriArrayList::add);
        model.addAttribute("nabori",naboriArrayList.get(0));
        Iterable<Sklad> sklads = skladRepository.findAll();
        model.addAttribute("sklad",sklads);
        return "nabori/edit-nabori";
    }


    @PostMapping("/edit/{id}")
    public String editNabori(
            @PathVariable("id") Long id,
            @ModelAttribute("nabori") @Valid Nabori newNabori,
            BindingResult bindingResult,
            Model model,@RequestParam String adres)
    {
        if(!naboriRepository.existsById(id))
        {
            return "redirect:/nabori";
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("nabori",newNabori);
            Iterable<Sklad> sklads = skladRepository.findAll();
            model.addAttribute("sklad",sklads);
            return "nabori/edit-nabori";
        }
        Sklad sklads = skladRepository.findByadresSklada(adres).get(0);
        newNabori.setAdressSklada(sklads);

        newNabori.setId(id);

        naboriRepository.save(newNabori);
        return "redirect:/nabori/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Nabori nabori = naboriRepository.findById(id).orElseThrow();
        naboriRepository.delete(nabori);
        return "redirect:/nabori/";
    }
}
