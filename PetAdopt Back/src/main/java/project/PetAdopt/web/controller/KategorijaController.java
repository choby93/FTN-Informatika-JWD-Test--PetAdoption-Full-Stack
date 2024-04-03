package project.PetAdopt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.PetAdopt.model.Kategorija;
import project.PetAdopt.service.KategorijaService;
import project.PetAdopt.support.KategorijaToKategorijaDto;
import project.PetAdopt.web.dto.KategorijaDto;

@RestController
@RequestMapping(value = "api/kategorije")
public class KategorijaController {

	@Autowired
	private KategorijaService kategorijaService;
	@Autowired
	private KategorijaToKategorijaDto kategorijaToDto;

	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<KategorijaDto>> getAll() {

		List<Kategorija> kategorije = kategorijaService.getAll();
		return new ResponseEntity<>(kategorijaToDto.convert(kategorije), HttpStatus.OK);

	}

}
