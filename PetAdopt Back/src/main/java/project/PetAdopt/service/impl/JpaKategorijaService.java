package project.PetAdopt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.PetAdopt.model.Kategorija;
import project.PetAdopt.repository.KategorijaRepository;
import project.PetAdopt.service.KategorijaService;

@Service
public class JpaKategorijaService implements KategorijaService {

	@Autowired
	private KategorijaRepository kategorijaRepository;

	@Override
	public List<Kategorija> getAll() {
		return kategorijaRepository.findAll();
	}

	@Override
	public Kategorija findOne(Long id) {
		Optional<Kategorija> kategorija = kategorijaRepository.findById(id);
		if (kategorija.isPresent()) {
			return kategorija.get();
		}
		return null;
	}

}
