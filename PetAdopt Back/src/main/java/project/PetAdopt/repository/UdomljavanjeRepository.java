package project.PetAdopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.PetAdopt.model.Udomljavanje;

@Repository
public interface UdomljavanjeRepository extends JpaRepository<Udomljavanje, Long> {

}
