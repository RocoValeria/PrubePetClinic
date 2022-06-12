package obsocial.modules.afiliado.fixture.afiliado;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.petowner.Plan;
import obsocial.modules.afiliado.dom.afiliado.Plan_addAfiliado;
import obsocial.modules.afiliado.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afiliado.dom.afiliado.TipoPlan;
import obsocial.modules.afiliado.fixture.plan.Plan_persona;

@Accessors(chain = true)
public class AfiliadoBuilder extends BuilderScriptWithResult<Afiliado> {

    @Getter @Setter String nombre;
    @Getter @Setter TipoPlan tipoPlan;
    @Getter @Setter Plan_persona plan_persona;

    @Override
    protected Afiliado buildResult(final ExecutionContext ec) {

        checkParam("nombre", ec, String.class);
        checkParam("tipoPlan", ec, TipoPlan.class);
        checkParam("plan_persona", ec, Plan_persona.class);

        Plan plan = ec.executeChildT(this, plan_persona.builder()).getObject();

        Afiliado afiliado = afiliadoRepository.findByPlanAndNombre(plan, nombre).orElse(null);
        if(afiliado == null) {
            wrapMixin(Plan_addAfiliado.class, plan).act(nombre, tipoPlan);
            afiliado = afiliadoRepository.findByPlanAndNombre(plan, nombre).orElseThrow();
        }

        return this.object = afiliado;
    }

    @Inject AfiliadoRepository afiliadoRepository;
}
