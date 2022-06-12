package obsocial.webapp.application.fixture.scenarios;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixturesService;

import obsocial.modules.afiliados.fixture.plan.Plan_persona;
import obsocial.modules.afiliados.fixture.plan.Plan_persona;

public class ObsocialDemo extends FixtureScript {

    @Override
    protected void execute(final ExecutionContext ec) {
        ec.executeChildren(this, moduleWithFixturesService.getTeardownFixture());
        ec.executeChild(this, new Afiliado_persona.PersistAll());
        ec.executeChild(this, new Plan_persona.PersistAll());
    }

    @Inject ModuleWithFixturesService moduleWithFixturesService;

}
