package obsocial.modules.afiliado.fixture.afiliado;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import lombok.AllArgsConstructor;
import lombok.Getter;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.plan.Plan;
import obsocial.modules.afiliado.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afiliado.dom.afiliado.TipoPlan;
import obsocial.modules.afiliado.fixture.plan.Plan_persona;

@AllArgsConstructor
public enum Afiliado_persona
implements PersonaWithBuilderScript<AfiliadoBuilder>, PersonaWithFinder<Afiliado> {

    Ramirez_luis("ramirez", TipoPlan.Oro, Plan_persona.Ramirez),
    Roman_abel("Roman", TipoPlan.Plata, Plan_persona.Roman),
    Hurli_azul("Azu", TipoPlan.Oro, Plan_persona.Hurli),
    Briant_amparo("ampa", TipoPlan.Bronce, Plan_persona.Briant),
    Tornal_lucia("luci", TipoPlan.Plata, Plan_persona.Tornal),
    Medil_camila("cam", TipoPlan.Bronce, Plan_persona.Medil),
    Berri_amadeo("amadeo", TipoPlan.Oro, Plan_persona.Berri);

    @Getter private final String nombre;
    @Getter private final TipoPlan tipoPlan;
    @Getter private final Plan_persona plan_persona;

    @Override
    public AfiliadoBuilder builder() {
        return new AfiliadoBuilder()
                        .setNombre(nombre)
                        .setTipoPlan(tipoPlan)
                        .setPlan_persona(plan_persona);
    }

    @Override
    public Afiliado findUsing(final ServiceRegistry serviceRegistry) {
        Plan plan = plan_persona.findUsing(serviceRegistry);
        AfiliadoRepository afiliadoRepository = serviceRegistry.lookupService(AfiliadoRepository.class).orElseThrow();
        return afiliadoRepository.findByPlanAndNombre(plan, nombre).orElse(null);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<Afiliado_persona, Afiliado> {
        public PersistAll() {
            super(Afiliado_persona.class);
        }
    }
}
