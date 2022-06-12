package obsocial.modules.obsocial.contributions.afiliado;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.clock.ClockService;
import org.apache.isis.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

import obsocial.modules.afilliado.dom.afiliado.Afiliado;
import obsocial.modules.afilliado.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afilliado.dom.afiliado.TipoAfiliao;
import obsocial.modules.afilliado.dom.plan.Plan;
import obsocial.modules.afilliado.types.AfiliadoName;
import obsocial.modules.obsocial.dom.asignar.Asignar;
import obsocial.modules.obsocial.types.Reason;

@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        commandPublishing = Publishing.ENABLED,
        executionPublishing = Publishing.ENABLED
)
@ActionLayout(associateWith = "asignar", sequence = "1")
@RequiredArgsConstructor
public class Afiliado_bookVisit {

    private final Afiliado afiliado;

    public Asignar act(
            LocalDateTime asignarAt,
            @Reason final String reason
            ) {
        return repositoryService.persist(new Asignar(afiliado, asignarAt, reason));
    }
    public String validate0Act(LocalDateTime asignarAt) {
        return clockService.getClock().nowAsLocalDateTime().isBefore(asignarAt)
                ? null
                : "ahora no";
    }
    public LocalDateTime default0Act() {
        return clockService.getClock().nowAsLocalDateTime()
                .toLocalDate()
                .plusDays(1)
                .atTime(LocalTime.of(9, 0));
    }

    @Inject ClockService clockService;
    @Inject RepositoryService repositoryService;
}
