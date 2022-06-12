package obsocial.webapp.application.services.homepage;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.Nature;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.Afiliado.AfiliadoRepository;
import obsocial.modules.afiliado.dom.plan.Plan;
import obsocial.modules.afiliado.dom.plan.PlanRepository;
import obsocial.modules.asignar.dom.asignar.AsignarRepository;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        logicalTypeName = "obsocial.HomePageViewModel"
        )
@HomePage
@DomainObjectLayout()
public class HomePageViewModel {

    public String title() {
        return  " plan" + getPlan().size();
    }

    public List<Plan> getPlan() {
        return planRepository.findAll();
    }
    public List<Afiliado> getAfiliados() {
        return afiliadoRepository.findAll();
    }
    public List<AsignarPlusPlan> getMostrar() {
        return asignarRepository.findAll()
                .stream()
                .map(AsignarPlusPlan::new)
                .collect(Collectors.toList());
    }

    @Inject PlanRepository planRepository;
    @Inject AfiliadoRepository afiliadoRepository;
    @Inject AsignarRepository asignarRepository;
}
