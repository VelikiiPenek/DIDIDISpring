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
@RequestMapping("/zapisi")
@PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
public class ZapisiController {


    @Autowired
    private ZapisiRepository zapisiRepository;
    @Autowired
    private KlientRepository klientRepository;
    @Autowired
    private YslygiRepository yslygiRepository;
    @Autowired
    private SotrydnikiRepository sotrydnikiRepository;


    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Zapisi> zapisis = zapisiRepository.findAll();
        model.addAttribute("zapisi",zapisis);
        return "zapisi/index";
    }

    @GetMapping("/add")
    public String addView(Model model, Zapisi zapisi)
    {
        model.addAttribute("zapsisi",new Zapisi());
        Iterable<Klient> klients = klientRepository.findAll();
        model.addAttribute("klient",klients);
        Iterable<Yslygi> yslygis = yslygiRepository.findAll();
        model.addAttribute("yslygi",yslygis);
        Iterable<Sotrydniki> sotrydnikis = sotrydnikiRepository.findAll();
        model.addAttribute("sotrydniki",sotrydnikis);
        return "zapisi/add-zapisi";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("zapisi")
            @Valid Zapisi newZapisi,
            BindingResult bindingResult,
            Model model,
            @RequestParam String klient,
            @RequestParam String yslygi,@RequestParam String sotrydniki)
    {
//        if(bindingResult.hasErrors()) {
//            model.addAttribute("zapisi",newZapisi);
//            Iterable<Klient> klients = klientRepository.findAll();
//            model.addAttribute("klient",klients);
//            Iterable<Yslygi> yslygis = yslygiRepository.findAll();
//            model.addAttribute("yslygi",yslygis);
//            Iterable<Sotrydniki> sotrydnikis = sotrydnikiRepository.findAll();
//            model.addAttribute("sotrydniki",sotrydnikis);
//            return "zapisi/add-zapisi";
//        }
        Klient klient1 = klientRepository.findBysurename(klient).get(0);
        newZapisi.setKlient(klient1);
        Yslygi yslygi1 = yslygiRepository.findBynameYslygi(yslygi).get(0);
        newZapisi.setYslygi(yslygi1);
        Sotrydniki sotrydniki1 = sotrydnikiRepository.findBysurename(sotrydniki).get(0);
        newZapisi.setSotrydniki(sotrydniki1);

        zapisiRepository.save(newZapisi);
        return "redirect:/zapisi/";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("zapisi") String Zapisi,
            Model model)
    {
        List<Zapisi> newsList = zapisiRepository.findBydataZapisi(Zapisi);
        model.addAttribute("zapisi",newsList);
        return "zapisi/index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Zapisi> zapisi = zapisiRepository.findById(id);
        ArrayList<Zapisi> zapisiArrayList = new ArrayList<>();
        zapisi.ifPresent(zapisiArrayList::add);
        model.addAttribute("zapisi",zapisiArrayList);
        return "zapisi/info-zapisi";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model
    )
    {
        if(!zapisiRepository.existsById(id))
        {
            return "redirect:/zapisi/";
        }
        Optional<Zapisi> zapisi = zapisiRepository.findById(id);
        ArrayList<Zapisi> zapisiArrayList = new ArrayList<>();
        zapisi.ifPresent(zapisiArrayList::add);
        model.addAttribute("zapisi",zapisiArrayList.get(0));
        Iterable<Klient> klients = klientRepository.findAll();
        model.addAttribute("klient",klients);
        Iterable<Yslygi> yslygis = yslygiRepository.findAll();
        model.addAttribute("yslygi",yslygis);
        Iterable<Sotrydniki> sotrydnikis = sotrydnikiRepository.findAll();
        model.addAttribute("sotrydniki",sotrydnikis);
        return "zapisi/edit-zapisi";
    }


    @PostMapping("/edit/{id}")
    public String editCars(
            @PathVariable("id") Long id,
            @ModelAttribute("zapisi") @Valid Zapisi newZapisi,
            BindingResult bindingResult,
            Model model,@RequestParam String klient,@RequestParam String yslygi,@RequestParam String sotrydniki)
    {
        if(!zapisiRepository.existsById(id))
        {
            return "redirect:/zapisi";
        }
//        if(bindingResult.hasErrors()) {
//            model.addAttribute("zapisi",newZapisi);
//            Iterable<Klient> klients = klientRepository.findAll();
//            model.addAttribute("klient",klients);
//            Iterable<Yslygi> yslygis = yslygiRepository.findAll();
//            model.addAttribute("yslygi",yslygis);
//            Iterable<Sotrydniki> sotrydnikis = sotrydnikiRepository.findAll();
//            model.addAttribute("sotrydniki",sotrydnikis);
//            return "yslygi/edit-yslygi";
//        }
        Klient klient1 = klientRepository.findBysurename(klient).get(0);
        newZapisi.setKlient(klient1);
        Yslygi yslygi1 = yslygiRepository.findBynameYslygi(yslygi).get(0);
        newZapisi.setYslygi(yslygi1);
        Sotrydniki sotrydniki1 = sotrydnikiRepository.findBysurename(sotrydniki).get(0);
        newZapisi.setSotrydniki(sotrydniki1);

        newZapisi.setId(id);

        zapisiRepository.save(newZapisi);
        return "redirect:/zapisi/";
    }

    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Zapisi zapisi = zapisiRepository.findById(id).orElseThrow();
        zapisiRepository.delete(zapisi);
        return "redirect:/zapisi/";
    }
}
