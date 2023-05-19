package com.uahb.m1info.hopitale.controller;

import com.uahb.m1info.hopitale.exception.MedecinNotFoundException;
import com.uahb.m1info.hopitale.model.Medecin;
import com.uahb.m1info.hopitale.model.Specialite;
import com.uahb.m1info.hopitale.repository.MedecinRepository;
import com.uahb.m1info.hopitale.repository.ServiceRepository;
import com.uahb.m1info.hopitale.repository.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/medecin")
public class MedecinController {

    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private SpecialiteRepository specialiteRepository;
    @GetMapping
    public List<Medecin> index(){
        return medecinRepository.findAll();
    }

    @PostMapping
    public Medecin save(@RequestBody Medecin medecin){
        medecin.setService(serviceRepository.findById(medecin.getService().getId()).get());
        List<Specialite> specialites = new ArrayList<>();
        medecin.getSpecialites().forEach(s ->{
            specialites.add(specialiteRepository.findById(s.getId()).get());
        });
        medecin.setSpecialites(specialites);
        return medecinRepository.save(medecin);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> savem(@RequestBody Medecin medecin){
        medecin.setService(serviceRepository.findById(medecin.getService().getId()).get());
        List<Specialite> specialites = new ArrayList<>();
        medecin.getSpecialites().forEach(s ->{
            specialites.add(specialiteRepository.findById(s.getId()).get());
        });
        medecin.setSpecialites(specialites);
        Medecin m = medecinRepository.save(medecin);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medecin.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Medecin find(@PathVariable long id){
        Optional <Medecin> m = medecinRepository.findById(id);
        if (m.isEmpty()){
            throw new MedecinNotFoundException("medecin avec id:"+id+ "introuvable");
        }
        return m.get();
    }

    @GetMapping("/edit/{id}")
    public EntityModel<Medecin> edit(@PathVariable long id){
        Optional <Medecin> m = medecinRepository.findById(id);
        if (m.isEmpty()){
            throw new MedecinNotFoundException("medecin avec id:"+id+ "introuvable");
        }
        EntityModel model = EntityModel.of(m.get());
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).index());
        model.add(webMvcLinkBuilder.withRel("all-medecins"));
        return model;
    }
}
