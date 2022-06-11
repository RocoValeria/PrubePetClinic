package obsocial.modules.afiliado.dom.plan;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.events.domain.ActionDomainEvent;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

@Action(
        domainEvent = Plan_delete.ActionEvent.class,
        semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(
        associateWith = "nombre", position = ActionLayout.Position.PANEL,
        describedAs = "elimina este objeto del almacen de datos persistente")
@RequiredArgsConstructor
public class Plan_delete {

    public static class ActionEvent extends ActionDomainEvent<Plan_delete>{}

    private final Plan plan;

    public void act() {
        repositoryService.remove(plan);
        return;
    }

    @Inject RepositoryService repositoryService;
}
