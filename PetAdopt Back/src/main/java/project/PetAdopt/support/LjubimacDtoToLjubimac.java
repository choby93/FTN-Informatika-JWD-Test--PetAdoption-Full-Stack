package project.PetAdopt.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.PetAdopt.model.Ljubimac;
import project.PetAdopt.service.KategorijaService;
import project.PetAdopt.service.LjubimacService;
import project.PetAdopt.web.dto.LjubimacDto;

@Component
public class LjubimacDtoToLjubimac implements Converter<LjubimacDto, Ljubimac> {

	@Autowired
	private LjubimacService ljubimacService;
	@Autowired
	private KategorijaService kategorijaService;

	@Override
	public Ljubimac convert(LjubimacDto dto) {

		Ljubimac ljubimac = null;
		if (dto.getId() == null) {
			ljubimac = new Ljubimac();
		} else {
			ljubimac = ljubimacService.getOne(dto.getId());
		}

		if (ljubimac != null) {
			ljubimac.setIme(dto.getIme());
			ljubimac.setStarost(dto.getStarost());
			if (dto.getVakcinisan() == null) {
				ljubimac.setVakcinisan(false);
			}
			ljubimac.setPol(dto.getPol());
			ljubimac.setTezina(dto.getTezina());
			ljubimac.setOpis(dto.getOpis());
			ljubimac.setKategorija(kategorijaService.findOne(dto.getKategorijaId()));

		}

		return ljubimac;
	}

}
