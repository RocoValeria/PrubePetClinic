package petclinic.modules.pets.dom.petowner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

import petclinic.modules.pets.types.PetName;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "pets", sequence = "2")
@RequiredArgsConstructor
public class PetOwner_removePet {

    private final PetOwner petOwner;

    public PetOwner act(@PetName final String name) {
        petRepository.findByPetOwnerAndName(petOwner, name)
                .ifPresent(pet -> repositoryService.remove(pet));
        return petOwner;
    }
    public List<String> choices0Act() {
        return petRepository.findByPetOwner(petOwner)
                .stream()
                .map(Pet::getName)
                .collect(Collectors.toList());
    }

    @Inject PetRepository petRepository;
    @Inject RepositoryService repositoryService;
}