package project.PetAdopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.PetAdopt.model.Kategorija;

@Repository
public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

}
