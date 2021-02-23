package com.sesame.Cars.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sesame.Cars.entities.Fabriquant;
import com.sesame.Cars.entities.Voiture;
import com.sesame.Cars.repositories.FabriquantRepository;
import com.sesame.Cars.repositories.VoitureRepository;

// ce'est moi
@Controller
@RequestMapping("voiture")
public class VoitureController {

	private final VoitureRepository voitureRepository;
	private final FabriquantRepository fabriquantRepository;
	
	@Autowired
	public VoitureController(VoitureRepository voitureRepository, FabriquantRepository fabriquantRepository) {
		this.voitureRepository = voitureRepository;
		this.fabriquantRepository = fabriquantRepository;
	}
	
	
	@GetMapping("list")
	public String listProviders(Model model) {
		// model.addAttribute("articles", null);

		List<Voiture> la = (List<Voiture>) voitureRepository.findAll();

		if (la.size() == 0)
			la = null;

		model.addAttribute("voitures", la);
		return "voiture/listVoiture";
	}
	
	
	// ajouter des voitures
	
	@GetMapping("add")
	public String showAddVoitureForm(Model model) {

		model.addAttribute("fabriquants", fabriquantRepository.findAll());
		model.addAttribute("voiture", new Voiture());
		return "voiture/addVoiture";
	}
	
	@PutMapping("add")
	// @ResponseBody
	public String ajoutVoiture(@Valid Voiture voiture, BindingResult result,
			@RequestParam(name = "fabriquantId", required = false) Long p,Model model) {
		
		
		if (result.hasErrors()) {
			model.addAttribute("fabriquants", voitureRepository.findAll());
			return "voiture/addVoiture";
		}

		Fabriquant fabriquant = fabriquantRepository.findById(p)
				.orElseThrow(() -> new IllegalArgumentException("Invalid fabriquant Id:" + p));
		
		voiture.setFabriquant(fabriquant);

		voitureRepository.save(voiture);
		return "redirect:list";

		// return article.getLabel() + " " +article.getPrice() + " " + p.toString();
	}
	
	
	
	// effacer des voitures
	@GetMapping("delete/{id}")
	public String deleteVoiture(@PathVariable("id") long id, Model model) {
		Voiture voiture = voitureRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid fabriquant Id:" + id));
		
		voitureRepository.delete(voiture);
		return "redirect:../list";
	}

	
	// editer des voitures

	@GetMapping("edit/{id}")
	public String showVoitureFormToUpdate(@PathVariable("id") long id, Model model) {
		Voiture voiture = voitureRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid fabriquant Id:" + id));

		model.addAttribute("voiture", voiture);
		model.addAttribute("fabriquants", fabriquantRepository.findAll());
		model.addAttribute("idFabriquant", voiture.getFabriquant().getId_f());

		return "voiture/updateVoiture";
	}

	@PostMapping("edit")
	public String updateVoiture(@Valid Voiture voiture, BindingResult result, Model model,
			@RequestParam(name = "FabriquantId", required = false) Long p) {
		if (result.hasErrors()) {
			
			return "voiture/updatevoiture";
		}

		Fabriquant fabriquant = fabriquantRepository.findById(p)
				.orElseThrow(() -> new IllegalArgumentException("Invalid fabriquant Id:" + p));
		voiture.setFabriquant(fabriquant);

		voitureRepository.save(voiture);
		return "redirect:list";
	}
	
	//commander une voiture
	
//	@GetMapping("commande/{id}")
//	public String showVoitureFormTocommander(@PathVariable("id") long id, Model model) {
//		Voiture voiture = voitureRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid voiture Id:" + id));
//		model.addAttribute("voiture", voiture);
//		return "voiture/commande";
//	}
//
//	
//	@PostMapping("commande")
//	public String commandeVoiture(@Valid Voiture voiture,
//			@RequestParam(name = "nom-acheteur")String nom,@RequestParam(name = "prenom-acheteur") String prenom,
//					@RequestParam(name = "email")String email,@RequestParam(name = "address")String address,
//					required = false) Long p) {
//
//		Fabriquant fabriquant = fabriquantRepository.findById(p)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid fabriquant Id:" + p));
//		voiture.setFabriquant(fabriquant);
//
//		voitureRepository.save(voiture);
//		return "redirect:list";
//	}
}



