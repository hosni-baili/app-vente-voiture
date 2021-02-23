package com.sesame.Cars.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sesame.Cars.entities.Fabriquant;
import com.sesame.Cars.entities.User;
import com.sesame.Cars.entities.Voiture;
import com.sesame.Cars.repositories.FabriquantRepository;
import com.sesame.Cars.repositories.VoitureRepository;

import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;
import java.io.IOException;
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
			@RequestParam(name = "fabriquantId", required = false) Long p) {
		if (result.hasErrors()) {
			
			return "voiture/updateVoiture";
		}

		Fabriquant fabriquant = fabriquantRepository.findById(p)
				.orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + p));
		voiture.setFabriquant(fabriquant);

		voitureRepository.save(voiture);
		return "redirect:list";
	}
	
	//commander une voiture
//	
//	@GetMapping("commande/{id}")
//	public String showVoitureFormTocommander(@PathVariable("id") long id, Model model) {
//		Voiture voiture = voitureRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid voiture Id:" + id));
//		model.addAttribute("voiture", voiture);
//		return "voiture/commander";
//	}
//
//	@Autowired
//	 private JavaMailSender javaMailSender;
//	
//	@PostMapping("achat/{id}")
//	@ResponseBody
//    public String confirmeAchat(@PathVariable ("id") long id, @RequestParam("name")String email,
//    		@RequestParam("name")String nom,@RequestParam("name")String prenom) {
//		 sendEmail(email,nom,prenom);
//		 Voiture voiture = voitureRepository.findById(id)
//				 .orElseThrow(()->new IllegalArgumentException("Invalid voiture Id:" + id));
//	     voitureRepository.delete(voiture);
//    	return "voiture/achatreussi";
//    }
//	
//	  void sendEmail(String email,String nom,String prenom) {
//
//	        SimpleMailMessage msg = new SimpleMailMessage();
//	        msg.setTo(email);
//	        msg.setSubject("Achat de voiture");
//	        msg.setText("Hello, "+nom+prenom+"  ton achat a ete effectuee avec succes. ");
//	        javaMailSender.send(msg);
//
//	    }
}



