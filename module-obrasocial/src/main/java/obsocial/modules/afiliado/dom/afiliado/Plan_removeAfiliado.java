package obsocial.modules.afiliado.dom.afiliado;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afiliado.dom.plan.Plan;
import obsocial.modules.afiliado.types.Plan;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "afiliados", sequence = "2")
@RequiredArgsConstructor
public class Plan_removeAfiliado {

    private final Plan plan;

    public Plan act(@Plan final String nombre) {
        afiliadoRepository.findByPlanAndName(plan, nombre)
                .ifPresent(afiliado -> repositoryService.remove(afiliado));
        return plan;
    }
    public String disableAct() {
        return afiliadoRepository.findByPlan(plan).isEmpty() ? "No existe" : null;
    }
    public List<String> choices0Act() {
        return afiliadoRepository.findByPlan(plan)
                .stream()
                .map(Afiliaado::getName)
                .collect(Collectors.toList());
    }
    public String default0Act() {
        List<String> nombres = choices0Act();
        return nombres.size() == 1 ? nombres.get(0) : null;
    }

    @Inject AfiliadoRepository afiliadoRepository;
    @Inject RepositoryService repositoryService;
}
