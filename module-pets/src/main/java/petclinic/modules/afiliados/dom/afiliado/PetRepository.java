package petclinic.modules.pets.dom.pet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import petclinic.modules.pets.dom.petowner.PetOwner;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByPetOwner(PetOwner petOwner);
    Optional<Pet> findByPetOwnerAndName(PetOwner petOwner, String name);
}
