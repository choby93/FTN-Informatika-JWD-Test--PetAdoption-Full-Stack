package project.PetAdopt.web.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.PetAdopt.model.Ljubimac;
import project.PetAdopt.model.Udomljavanje;
import project.PetAdopt.service.LjubimacService;
import project.PetAdopt.service.UdomljavanjeService;
import project.PetAdopt.support.LjubimacDtoToLjubimac;
import project.PetAdopt.support.LjubimacToLjubimacDto;
import project.PetAdopt.web.dto.LjubimacDto;

@RestController
@RequestMapping(value = "api/ljubimci", produces = MediaType.APPLICATION_JSON_VALUE)
public class LjubimacController {

	@Autowired
	private LjubimacService ljubimacService;
	@Autowired
	private LjubimacToLjubimacDto ljubimacToDto;
	@Autowired
	private LjubimacDtoToLjubimac toEntity;
	@Autowired
	private UdomljavanjeService udomljavanjeService;

	@PreAuthorize("permitAll()")
	@GetMapping
	public ResponseEntity<List<LjubimacDto>> getAll(@RequestParam(required = false) Long kategorijaId,
			@RequestParam(required = false) String pol, @RequestParam(required = false) String opis,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

		Page<Ljubimac> page = ljubimacService.search(kategorijaId, opis, pol, pageNo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("total-pages", Integer.toString(page.getTotalPages()));
		return new ResponseEntity<>(ljubimacToDto.convert(page.getContent()), headers, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<LjubimacDto> getOne(@PathVariable Long id) {

		Ljubimac ljubimac = ljubimacService.getOne(id);
		if (ljubimac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ljubimacToDto.convert(ljubimac), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LjubimacDto> create(@Valid @RequestBody LjubimacDto ljubimacDto) {

		Ljubimac ljubimac = toEntity.convert(ljubimacDto);
		Ljubimac sacuvanLjubimac = ljubimacService.save(ljubimac);
		return new ResponseEntity<>(ljubimacToDto.convert(sacuvanLjubimac), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LjubimacDto> update(@PathVariable Long id, @Valid @RequestBody LjubimacDto dto) {

		if (dto.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Ljubimac ljubimac = toEntity.convert(dto);
		Ljubimac sacuvanLjubimac = ljubimacService.update(ljubimac);
		return new ResponseEntity<>(ljubimacToDto.convert(sacuvanLjubimac), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LjubimacDto> delete(@PathVariable Long id) {

		Ljubimac ljubimac = ljubimacService.delete(id);
		if (ljubimac != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}/vakcinacija")
	public ResponseEntity<Void> vakcinacija(@PathVariable Long id) {

		Ljubimac ljubimac = ljubimacService.getOne(id);
		if (ljubimac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		ljubimacService.vakcinacija(ljubimac);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_KORISNIK')")
	@PostMapping(value = "/{id}")
	public ResponseEntity<Void> udomljavanje(@PathVariable Long id) {
		Ljubimac ljubimac = ljubimacService.getOne(id);

		if (ljubimac.getKategorija() == null || ljubimac.getUdomljavanje() != null || !ljubimac.isVakcinisan()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Udomljavanje udomljavanje = new Udomljavanje(LocalDate.now());
		Udomljavanje udomljenaZivotinja = udomljavanjeService.save(udomljavanje);

		ljubimac.setUdomljavanje(udomljenaZivotinja);
		ljubimacService.save(ljubimac);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
