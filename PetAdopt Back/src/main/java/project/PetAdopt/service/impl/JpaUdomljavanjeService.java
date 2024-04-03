package project.PetAdopt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.PetAdopt.model.Udomljavanje;
import project.PetAdopt.repository.UdomljavanjeRepository;
import project.PetAdopt.service.UdomljavanjeService;

@Service
public class JpaUdomljavanjeService implements UdomljavanjeService {

	@Autowired
	private UdomljavanjeRepository udomljavanjeRepository;

	@Override
	public Udomljavanje save(Udomljavanje udomljavanje) {
		return udomljavanjeRepository.save(udomljavanje);
	}

}
