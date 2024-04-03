package project.PetAdopt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kategorija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String Naziv;
	@OneToMany(mappedBy = "kategorija")
	private List<Ljubimac> ljubimci = new ArrayList<>();

	public Kategorija() {
	}

	public Kategorija(Long id, String naziv, List<Ljubimac> ljubimci) {
		super();
		this.id = id;
		Naziv = naziv;
		this.ljubimci = ljubimci;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return Naziv;
	}

	public void setNaziv(String naziv) {
		Naziv = naziv;
	}

	public List<Ljubimac> getLjubimci() {
		return ljubimci;
	}

	public void setLjubimci(List<Ljubimac> ljubimci) {
		this.ljubimci = ljubimci;
	}

}
