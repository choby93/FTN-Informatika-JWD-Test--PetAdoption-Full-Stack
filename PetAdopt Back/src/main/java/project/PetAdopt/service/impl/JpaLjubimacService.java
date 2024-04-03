package project.PetAdopt.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import project.PetAdopt.model.Ljubimac;
import project.PetAdopt.repository.LjubimacRepository;
import project.PetAdopt.service.LjubimacService;

@Service
public class JpaLjubimacService implements LjubimacService {

	@Autowired
	private LjubimacRepository ljubimacRepository;

	@Override
	public Ljubimac getOne(Long id) {
		Optional<Ljubimac> ljubimacOptional = ljubimacRepository.findById(id);
		if (ljubimacOptional.isPresent()) {
			return ljubimacOptional.get();
		}
		return null;
	}

	@Override
	public Ljubimac save(Ljubimac ljubimac) {
		return ljubimacRepository.save(ljubimac);
	}

	@Override
	public Ljubimac update(Ljubimac ljubimac) {
		return ljubimacRepository.save(ljubimac);
	}

	@Override
	public Ljubimac delete(Long id) {
		Ljubimac ljubimac = getOne(id);
		ljubimacRepository.delete(ljubimac);
		return ljubimac;
	}

	@Override
	public Page<Ljubimac> search(Long kategorijaId, String opis, String pol, Integer pageNo) {
		return ljubimacRepository.search(kategorijaId, opis, pol, PageRequest.of(pageNo, 3));
	}

	@Override
	public void vakcinacija(Ljubimac ljubimac) {

		boolean isVakcinisan = ljubimac.isVakcinisan();
		boolean novaVrednost = !isVakcinisan;
		ljubimac.setVakcinisan(novaVrednost);
		ljubimacRepository.save(ljubimac);
	}

}
