package obsocial.modules.afiliado.dom.afiliado;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import org.apache.isis.applib.services.repository.RepositoryService;

import obsocial.modules.afiliado.dom.plan.Plan;
import obsocial.modules.afiliado.dom.plan.Plan_delete;

@Service
public class PlanForAfiliadoSubscriber {

    @EventListener(Plan_delete.ActionEvent.class)
    public void on(Plan_delete.ActionEvent ev) {
        switch(ev.getEventPhase()) {
            case EXECUTING:
                Plan plan = ev.getSubject();
                List<Afiliado> afiliados = afiliadoRepository.findByPlan(plan);
                afiliados.forEach(repositoryService::remove);
                break;
        }
    }

    @Inject AfiliadoRepository afiliadoRepository;
    @Inject RepositoryService repositoryService;
}
