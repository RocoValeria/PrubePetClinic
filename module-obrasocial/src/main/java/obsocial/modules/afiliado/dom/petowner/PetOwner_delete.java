package petclinic.modules.pets.dom.petowner;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.events.domain.ActionDomainEvent;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

@Action(
        domainEvent = PetOwner_delete.ActionEvent.class,
        semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(
        associateWith = "name", position = ActionLayout.Position.PANEL,
        describedAs = "Deletes this object from the persistent datastore")
@RequiredArgsConstructor
public class PetOwner_delete {

    public static class ActionEvent extends ActionDomainEvent<PetOwner_delete>{}

    private final PetOwner petOwner;

    public void act() {
        repositoryService.remove(petOwner);
        return;
    }

    @Inject RepositoryService repositoryService;
}
