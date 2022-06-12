package obsocial.modules.Asignar.subscribers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afiliado.dom.plan.Plan;
import obsocial.modules.afiliado.dom.plan.Plan_delete;
import obsocial.modules.afiliado.dom.asignar.Asignar;
import obsocial.modules.asignar.dom.asignar.AsignarRepository;

@Service
public class PlanForAsignarSubscriber {

    @EventListener(Plan_delete.ActionEvent.class)
    public void on(Plan_delete.ActionEvent ev) {
        switch(ev.getEventPhase()) {
            case DISABLE:
                Plan plan = ev.getSubject();
                List<Afiliado> afiliados = afiliadoRepository.findByPlan(plan);
                for (Afiliado afiliado : afiliados) {
                    List<Asignar> mostrar = asignarRepository.findByPlanByAsignarAtDesc(pet);
                    int numMostrar = mostrar.size();
                    if(numMostrar > 0) {
                        ev.disable(String.format("%s has %d visit%s",
                                titleService.titleOf(afiliado),
                                numMostrar,
                                numMostrar != 1 ? "s" : ""));
                    }
                }
                break;
        }
    }

    @Inject TitleService titleService;
    @Inject AsignarRepository asignarRepository;
    @Inject AfiliadoRepository afiliadoRepository;
}
