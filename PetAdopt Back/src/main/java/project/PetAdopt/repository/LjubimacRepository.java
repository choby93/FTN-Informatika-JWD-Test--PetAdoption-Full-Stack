package project.PetAdopt.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.PetAdopt.model.Ljubimac;

@Repository
public interface LjubimacRepository extends JpaRepository<Ljubimac, Long> {

	@Query("SELECT lj FROM Ljubimac lj WHERE"
			+ "(:kategorijaId IS NULL OR lj.kategorija.id LIKE :kategorijaId) AND"
			+ "(:opis IS NULL OR lj.opis LIKE %:opis%) AND"
			+ "(:pol IS NULL OR lj.pol LIKE %:pol%)")
	
	Page<Ljubimac> search(
			@Param("kategorijaId") Long kategorijaId, 
			@Param("opis")String opis, 
			@Param("pol")String pol, Pageable pageNo);
}