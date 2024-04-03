package project.PetAdopt.service;

import java.util.List;

import project.PetAdopt.model.Kategorija;

public interface KategorijaService {

	List<Kategorija> getAll();

	Kategorija findOne(Long id);

}
