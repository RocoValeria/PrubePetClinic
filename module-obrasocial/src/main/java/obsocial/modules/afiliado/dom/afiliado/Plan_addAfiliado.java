package obsocial.modules.afiliado.dom.afiliado;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

import obsocial.modules.afiliados.dom.afiliado.Afiliado;
import obsocial.modules.afiliados.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afiliados.dom.afiliado.TipoPlan;
import obsocial.modules.afiliados.dom.plan.Plan;
import obsocial.modules.afiliados.types.AfiliadoNombre;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "afiliados", sequence = "1")
@RequiredArgsConstructor
public class Plan_addAfiliado {

    private final Plan plan;

    public Plan act(
            @AfiliadoNombre final String nombre,
            final TipoPlan tipoPlan
            ) {
        repositoryService.persist(new Afiliado(plan, nombre, tipoPlan));
        return plan;
    }
    public String validate0Act(final String nombre) {
        return afiliadoRepository.findByPlanNombre(plan, nombre).isPresent()
                ? String.format("El afiliado '%s' ya existe para este plan", nombre)
                : null;
    }
    public TipoPlan default1Act() {
        return TipoPlan.Ninguno;
    }

    @Inject AfiliadoRepository afiliadoRepository;
    @Inject RepositoryService repositoryService;
}
