package domainapp.webapp.application.fixture.scenarios;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixturesService;

import petclinic.modules.pets.fixture.PetOwner_persona;

public class DomainAppDemo extends FixtureScript {

    @Override
    protected void execute(final ExecutionContext ec) {
        ec.executeChildren(this, moduleWithFixturesService.getTeardownFixture());
        ec.executeChild(this, new PetOwner_persona.PersistAll());
    }

    @Inject ModuleWithFixturesService moduleWithFixturesService;

}
