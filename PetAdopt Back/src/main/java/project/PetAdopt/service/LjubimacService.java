package project.PetAdopt.service;

import org.springframework.data.domain.Page;

import project.PetAdopt.model.Ljubimac;

public interface LjubimacService {

	Ljubimac getOne(Long id);

	Ljubimac save(Ljubimac ljubimac);

	Ljubimac update(Ljubimac ljubimac);

	Ljubimac delete(Long id);

	Page<Ljubimac> search(Long kategorijaId, String opis,String pol, Integer pageNo);

	void vakcinacija(Ljubimac ljubimac);


}
