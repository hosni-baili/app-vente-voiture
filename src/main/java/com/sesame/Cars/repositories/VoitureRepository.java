package com.sesame.Cars.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sesame.Cars.entities.Voiture;


public interface VoitureRepository extends CrudRepository<Voiture, Long>{

}