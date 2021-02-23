package com.sesame.Cars.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class Voiture {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idv;

    @NotBlank(message = "le modele est obligatoire")
    @Column(name = "Modele")
    private String modele;

    @NotBlank(message = "la puissance est obligatoire")
    @Column(name = "Puissance")
    private String puissance;
    
    @NotBlank(message = "l'energie est obligatoire")
    @Column(name = "Energie")
    private String energie;
    
    @Min(value=100)
    @Column(name = "Prix")
    private float prix;
    

	public Voiture() {
		super();
	}

	public Voiture(long idv, @NotBlank(message = "le modele est obligatoire") String modele,
			@NotBlank(message = "la puissance est obligatoire") String puissance,
			@NotBlank(message = "l'energie est obligatoire") String energie,
			@NotBlank(message = "le prix est obligatoire") float prix, Fabriquant fabriquant) {
		super();
		this.idv = idv;
		this.modele = modele;
		this.puissance = puissance;
		this.energie = energie;
		this.prix = prix;
		this.fabriquant = fabriquant;
	}

	public long getIdv() {
		return idv;
	}

	public void setIdv(long idv) {
		this.idv = idv;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getPuissance() {
		return puissance;
	}

	public void setPuissance(String puissance) {
		this.puissance = puissance;
	}

	public String getEnergie() {
		return energie;
	}

	public void setEnergie(String energie) {
		this.energie = energie;
	}


public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}


@Override
	public String toString() {
		return "Voiture [idv=" + idv + ", modele=" + modele + ", puissance=" + puissance + ", energie=" + energie
				+ ", prix=" + prix + ", fabriquant=" + fabriquant + "]";
	}


/**** Many To One ****/
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Fabriquant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabriquant fabriquant;

public Fabriquant getFabriquant() {
	return fabriquant;
}

public void setFabriquant(Fabriquant fabriquant) {
	this.fabriquant = fabriquant;
}
    
}

