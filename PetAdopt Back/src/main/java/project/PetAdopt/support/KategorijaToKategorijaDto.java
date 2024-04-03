package project.PetAdopt.support;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import project.PetAdopt.model.Kategorija;
import project.PetAdopt.web.dto.KategorijaDto;

@Component
public class KategorijaToKategorijaDto implements Converter<Kategorija, KategorijaDto> {

	@Override
	public KategorijaDto convert(Kategorija kategorija) {

		KategorijaDto dto = new KategorijaDto();

		dto.setId(kategorija.getId());
		dto.setNaziv(kategorija.getNaziv());

		return dto;
	}

	public List<KategorijaDto> convert(List<Kategorija> kategorije) {

		List<KategorijaDto> dtoS = kategorije.stream()
                .map(this::convert)
                .collect(Collectors.toList());

		return dtoS;
	}

}
