package petclinic.webapp.application.services.homepage;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.Nature;

import petclinic.modules.pets.dom.pet.Pet;
import petclinic.modules.pets.dom.pet.PetRepository;
import petclinic.modules.pets.dom.petowner.PetOwner;
import petclinic.modules.pets.dom.petowner.PetOwnerRepository;
import petclinic.modules.visits.dom.visit.VisitRepository;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        logicalTypeName = "petclinic.HomePageViewModel"
        )
@HomePage
@DomainObjectLayout()
public class HomePageViewModel {

    public String title() {
        return getPetOwners().size() + " owners";
    }

    public List<PetOwner> getPetOwners() {
        return petOwnerRepository.findAll();
    }
    public List<Pet> getPets() {
        return petRepository.findAll();
    }
    public List<VisitPlusPetOwner> getVisits() {
        return visitRepository.findAll()
                .stream()
                .map(VisitPlusPetOwner::new)
                .collect(Collectors.toList());
    }

    @Inject PetOwnerRepository petOwnerRepository;
    @Inject PetRepository petRepository;
    @Inject VisitRepository visitRepository;
}
