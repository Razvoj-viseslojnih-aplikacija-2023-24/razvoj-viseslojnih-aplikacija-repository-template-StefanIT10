package rva.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Rociste implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "ROCISTE_SEQ_GENERATOR", sequenceName = "ROCISTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROCISTE_SEQ_GENERATOR")
	private int id;
	private Date datumRocista;
	private String sudnica;
	
	@ManyToOne
	@JoinColumn(name = "ucesnik")
	private Ucesnik ucesnik;
	
	@ManyToOne
	@JoinColumn(name = "predmet")
	private Predmet predmet;
	
	
	public Rociste() {
	
	}
	
	
	
	public Rociste(int id, Date datumRocista, String sudnica) {
		this.id = id;
		this.datumRocista = datumRocista;
		this.sudnica = sudnica;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDatumRocista() {
		return datumRocista;
	}
	public void setDatumRocista(Date datumRocista) {
		this.datumRocista = datumRocista;
	}
	public String getSudnica() {
		return sudnica;
	}
	public void setSudnica(String sudnica) {
		this.sudnica = sudnica;
	}
	
	
	

}
