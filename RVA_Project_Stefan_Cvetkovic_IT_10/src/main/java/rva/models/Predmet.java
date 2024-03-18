package rva.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Predmet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "PREDMET_SEQ_GENERATOR", sequenceName = "PREDMET_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PREDMET_SEQ_GENERATOR")
	private int id;
	private String brojPr;
	private String opis;
	private Date datumPocetka;
	private boolean aktivan;
	
	@OneToMany(mappedBy = "predmet", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Rociste> rocista;
	
	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public List<Rociste> getRocista() {
		return rocista;
	}

	public void setRocista(List<Rociste> rocista) {
		this.rocista = rocista;
	}

	public Sud getSud() {
		return sud;
	}

	public void setSud(Sud sud) {
		this.sud = sud;
	}

	@ManyToOne
	@JoinColumn(name = "sud")
	private Sud sud;
	
	public Predmet() {
	
	}

	public Predmet(int id, String brojPr, String opis, Date datum_pocetka, boolean aktivan) {
		this.id = id;
		this.brojPr = brojPr;
		this.opis = opis;
		this.datumPocetka = datum_pocetka;
		this.aktivan = aktivan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrojPr() {
		return brojPr;
	}

	public void setBrojPr(String brojPr) {
		this.brojPr = brojPr;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getDatum_pocetka() {
		return datumPocetka;
	}

	public void setDatum_pocetka(Date datum_pocetka) {
		this.datumPocetka = datum_pocetka;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	
	

}
