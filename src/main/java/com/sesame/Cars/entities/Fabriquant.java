package com.sesame.Cars.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Fabriquant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_f;

    @NotBlank(message = "le nom du fabriqunt est obligatoire")
    @Column(name = "Nom_fabriquant")
    private String nomf;
    
    @NotBlank(message = "l'email est obligatoire")
    @Column(name = "email")
    private String email;
    
    @NotBlank(message = "l'address est obligatoire")
    @Column(name = "address")
    private String address;

	public Fabriquant() {
		super();
	}

	public Fabriquant(long id_f, @NotBlank(message = "le nom du fabriqunt est obligatoire") String nomf,
			@NotBlank(message = "l'email est obligatoire") String email,
			@NotBlank(message = "l'address est obligatoire") String address) {
		super();
		this.id_f = id_f;
		this.nomf = nomf;
		this.email = email;
		this.address = address;
	}

	public long getId_f() {
		return id_f;
	}

	public void setId_f(long id_f) {
		this.id_f = id_f;
	}

	public String getNomf() {
		return nomf;
	}

	public void setNomf(String nomf) {
		this.nomf = nomf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Fabriquant [id_f=" + id_f + ", nomf=" + nomf + ", email=" + email + ", address=" + address + "]";
	}
// commee
    
}
