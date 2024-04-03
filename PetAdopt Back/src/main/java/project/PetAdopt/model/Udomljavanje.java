package project.PetAdopt.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Udomljavanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate datum;
	@OneToOne(mappedBy = "udomljavanje")
	private Ljubimac ljubimac;

	public Udomljavanje() {
		super();
	}

	public Udomljavanje(LocalDate datum) {
		super();
		this.datum = datum;
	}

	public Udomljavanje(Long id, LocalDate datum, Ljubimac ljubimac) {
		super();
		this.id = id;
		this.datum = datum;
		this.ljubimac = ljubimac;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Ljubimac getLjubimac() {
		return ljubimac;
	}

	public void setLjubimac(Ljubimac ljubimac) {
		this.ljubimac = ljubimac;
	}

}
