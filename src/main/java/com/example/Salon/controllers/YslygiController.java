package com.example.Salon.controllers;


import com.example.Salon.models.Nabori;
import com.example.Salon.models.Sklad;
import com.example.Salon.models.TipYslygi;
import com.example.Salon.models.Yslygi;
import com.example.Salon.repositories.NaboriRepository;
import com.example.Salon.repositories.SkladRepository;
import com.example.Salon.repositories.TipYslygiRepostiory;
import com.example.Salon.repositories.YslygiRepository;
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
@RequestMapping("/yslygi")
@PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
public class YslygiController {


    @Autowired
    private YslygiRepository yslygiRepository;
    @Autowired
    private NaboriRepository naboriRepository;
    @Autowired
    private TipYslygiRepostiory tipYslygiRepostiory;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Yslygi> yslygis = yslygiRepository.findAll();
        model.addAttribute("yslygi",yslygis);
        return "yslygi/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Yslygi yslygi)
    {
        model.addAttribute("yslygi",new Yslygi());
        Iterable<Nabori> naboris = naboriRepository.findAll();
        model.addAttribute("nabori",naboris);
        Iterable<TipYslygi> tipYslygis = tipYslygiRepostiory.findAll();
        model.addAttribute("tipyslygi",tipYslygis);
        return "yslygi/add-yslygi";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("yslygi")
            @Valid Yslygi newYslygi,
            BindingResult bindingResult,
            Model model,
            @RequestParam String nabor,
            @RequestParam String tip)
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("yslygi",newYslygi);
            Iterable<Nabori> naboris = naboriRepository.findAll();
            model.addAttribute("nabori",naboris);
            Iterable<TipYslygi> tipYslygis = tipYslygiRepostiory.findAll();
            model.addAttribute("tipyslygi",tipYslygis);
            return "yslygi/add-yslygi";
        }
        Nabori naboris = naboriRepository.findBynameKit(nabor).get(0);
        newYslygi.setNabori(naboris);
        TipYslygi tipYslygis = tipYslygiRepostiory.findBytipYslygi(tip).get(0);
        newYslygi.setTipYslygi(tipYslygis);

        yslygiRepository.save(newYslygi);
        return "redirect:/yslygi/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("yslygi") String Yslygi,
            Model model)
    {
        List<Yslygi> newsList = yslygiRepository.findBynameYslygi(Yslygi);
        model.addAttribute("yslygi",newsList);
        return "yslygi/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Yslygi> yslygis = yslygiRepository.findById(id);
        ArrayList<Yslygi> yslygiArrayList = new ArrayList<>();
        yslygis.ifPresent(yslygiArrayList::add);
        model.addAttribute("yslygi",yslygiArrayList);
        return "yslygi/info-yslygi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!yslygiRepository.existsById(id))
        {
            return "redirect:/yslygi/";
        }
        Optional<Yslygi> yslygis = yslygiRepository.findById(id);
        ArrayList<Yslygi> yslygiArrayList = new ArrayList<>();
        yslygis.ifPresent(yslygiArrayList::add);
        model.addAttribute("yslygi",yslygiArrayList.get(0));
        Iterable<Nabori> naboris = naboriRepository.findAll();
        model.addAttribute("nabori",naboris);
        Iterable<TipYslygi> tipYslygis = tipYslygiRepostiory.findAll();
        model.addAttribute("tipyslygi",tipYslygis);
        return "yslygi/edit-yslygi";
    }


    @PostMapping("/edit/{id}")
    public String editCars(
            @PathVariable("id") Long id,
            @ModelAttribute("yslygi") @Valid Yslygi newYslygi,
            BindingResult bindingResult,
            Model model,@RequestParam String nabor,@RequestParam String tip)
    {
        if(!yslygiRepository.existsById(id))
        {
            return "redirect:/yslygi";
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("yslygi",newYslygi);
            Iterable<Nabori> naboris = naboriRepository.findAll();
            model.addAttribute("nabori",naboris);
            Iterable<TipYslygi> tipYslygis = tipYslygiRepostiory.findAll();
            model.addAttribute("tipyslygi",tipYslygis);
            return "yslygi/edit-yslygi";
        }
        Nabori naboris = naboriRepository.findBynameKit(nabor).get(0);
        newYslygi.setNabori(naboris);
        TipYslygi tipYslygis = tipYslygiRepostiory.findBytipYslygi(tip).get(0);
        newYslygi.setTipYslygi(tipYslygis);

        newYslygi.setId(id);

        yslygiRepository.save(newYslygi);
        return "redirect:/yslygi/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Yslygi yslygi = yslygiRepository.findById(id).orElseThrow();
        yslygiRepository.delete(yslygi);
        return "redirect:/yslygi/";
    }
}
