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
import org.springframework.web.bind.annotation.RequestMapping;

import com.sesame.Cars.entities.Fabriquant;
import com.sesame.Cars.repositories.FabriquantRepository;



@Controller
@RequestMapping("/fabriquant/")

public class FabriquantController {

	private final FabriquantRepository fabriquantRepository;

	@Autowired
	public FabriquantController(FabriquantRepository fabriquantRepository) {
		this.fabriquantRepository = fabriquantRepository;
	}

	@GetMapping("list")
	// @ResponseBody
	public String listFabriquants(Model model) {

		List<Fabriquant> lp = (List<Fabriquant>) fabriquantRepository.findAll();

		if (lp.size() == 0)
			lp = null;
		model.addAttribute("fabriquants", lp);

		return "fabriquant/listfabriquant";
	}

	@GetMapping("add")
	public String showAddFabriquantForm(Model model) {
		Fabriquant fabriquant = new Fabriquant();// object dont la valeur des attributs par defaut
		model.addAttribute("fabriquant", fabriquant);
		return "fabriquant/addfabriquant";
	}

	@PostMapping("add")
	public String addFabriquant(@Valid Fabriquant fabriquant, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "fabriquant/addfabriquant";
		}
		fabriquantRepository.save(fabriquant);
		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String deleteFabriquant(@PathVariable("id") long id, Model model) {

		// long id2 = 100L;

		Fabriquant fabriquant = fabriquantRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid fabriquant Id:" + id));

		fabriquantRepository.delete(fabriquant);

		return "redirect:../list";
	}
	

	@GetMapping("edit/{id}")
	public String showFabriquantFormToUpdate(@PathVariable("id") long id, Model model) {
		Fabriquant fabriquant = fabriquantRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid fabriquant Id:" + id));

		model.addAttribute("fabriquant", fabriquant);

		return "fabriquant/updateFabriquant";
	}

	@PostMapping("edit")
	public String updateFabriquant(@Valid Fabriquant fabriquant, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "fabriquant/updateFabriquant";
		}

		fabriquantRepository.save(fabriquant);
		return "redirect:list";

	}
}
