package project.PetAdopt.support;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongToIntFunction;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.PetAdopt.model.Ljubimac;
import project.PetAdopt.web.dto.LjubimacDto;

@Component
public class LjubimacToLjubimacDto implements Converter<Ljubimac, LjubimacDto> {

	@Override
	public LjubimacDto convert(Ljubimac ljubimac) {

		LjubimacDto dto = new LjubimacDto();

		dto.setId(ljubimac.getId());
		dto.setIme(ljubimac.getIme());
		dto.setStarost(ljubimac.getStarost());
		dto.setVakcinisan(ljubimac.isVakcinisan());
		dto.setPol(ljubimac.getPol());
		dto.setTezina(ljubimac.getTezina());
		dto.setOpis(ljubimac.getOpis());
		dto.setKategorijaId(ljubimac.getKategorija().getId());
		dto.setKategorijaNaziv(ljubimac.getKategorija().getNaziv());
		

		return dto;
	}

	public List<LjubimacDto> convert(List<Ljubimac> ljubimci) {

		List<LjubimacDto> dtoS = new ArrayList<>();

		for (Ljubimac lj : ljubimci) {
			dtoS.add(convert(lj));
		}

		return dtoS;
	}

}
