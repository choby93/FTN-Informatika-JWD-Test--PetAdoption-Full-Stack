package project.PetAdopt.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class LjubimacDto {

	private Long id;
	@NotBlank
	private String ime;

	private Integer starost;

	private Boolean vakcinisan;

	private String pol;
	@Positive
	private Double tezina;

	private String opis;

	private Long kategorijaId;

	private String kategorijaNaziv;
	
	private Long udomljavanjeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Integer getStarost() {
		return starost;
	}

	public void setStarost(Integer starost) {
		this.starost = starost;
	}

	public Boolean getVakcinisan() {
		return vakcinisan;
	}

	public void setVakcinisan(Boolean vakcinisan) {
		this.vakcinisan = vakcinisan;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public Double getTezina() {
		return tezina;
	}

	public void setTezina(Double tezina) {
		this.tezina = tezina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Long getKategorijaId() {
		return kategorijaId;
	}

	public void setKategorijaId(Long kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

	public String getKategorijaNaziv() {
		return kategorijaNaziv;
	}

	public void setKategorijaNaziv(String kategorijaNaziv) {
		this.kategorijaNaziv = kategorijaNaziv;
	}

	public Long getUdomljavanjeId() {
		return udomljavanjeId;
	}

	public void setUdomljavanjeId(Long udomljavanjeId) {
		this.udomljavanjeId = udomljavanjeId;
	}

	 
}
