package obsocial.webapp.application.services.homepage;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.services.factory.FactoryService;
import org.apache.isis.applib.services.wrapper.WrapperFactory;

import lombok.RequiredArgsConstructor;

import obsocial.modules.afiliados.dom.afiliado.Pet;
import obsocial.modules.afiliados.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afiliados.dom.plan.Plan;
import obsocial.modules.afiliados.dom.plan.PlanRepository;
import obsocial.modules.asignar.contributions.afiliado.Afiliado_bookVisit;
import obsocial.modules.asignar.dom.asignar.Asignar;

@Action
@RequiredArgsConstructor
public class HomePageViewModel_bookAsignar {

    final HomePageViewModel homePageViewModel;

    public Object act(Plan plan, Afiliado afiliado, LocalDateTime asignarAt, String reason, boolean showVisit) {
        Asignar asignar = wrapperFactory.wrapMixin(Afiliado_bookAsignar.class, afiliado).act(asignarAt, reason);
        return showAsignar ? asignar : homePageViewModel;
    }
    public List<Plan> autoComplete0Act(final String nombre) {
        return planRepository.findByNombreContaining(nombre);
    }
    public List<Afiliado> choices1Act(Plan plan) {
        if(plan == null) return Collections.emptyList();
        return afiliadoRepository.findByPlan(plan);
    }
    public LocalDateTime default2Act(Plan plan, Pet afiliado) {
        if(afiliado == null) return null;
        return factoryService.mixin(Afiliado_bookVisit.class, afiliado).default0Act();
    }
    public String validate2Act(Plan plan, Pet afiliado, LocalDateTime asignarAt){
         return factoryService.mixin(Afiliado_bookVisit.class, afiliado).validate0Act(asignarAt);
    }

    @Inject AfiliadoRepository afiliadoRepository;
    @Inject PlanRepository planRepository;
    @Inject WrapperFactory wrapperFactory;
    @Inject FactoryService factoryService;
}
