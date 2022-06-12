package obsocial.modules.afiliado.fixture.plan;

import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithBuilderScript;
import org.apache.isis.testing.fixtures.applib.personas.PersonaWithFinder;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import obsocial.modules.afiliado.dom.petowner.Plan;
import obsocial.modules.afiliado.dom.petowner.Planes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum plan
implements PlanWithBuilderScript<PlanBuilder>, PlanWithFinder<Plan> {

    Oro("Farmacia 70%"),
    Plata("Farmacia 50%"),
    Bronce("Farmacia 30%");


    @Getter
    private final String name;

    @Override
    public PetOwnerBuilder builder() {
        return new PetOwnerBuilder().setName(name);
    }

    @Override
    public PetOwner findUsing(final ServiceRegistry serviceRegistry) {
        PetOwners petOwners = serviceRegistry.lookupService(PetOwners.class).orElse(null);
        return petOwners.findByLastNameExact(name);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<PetOwner_persona, PetOwner> {

        public PersistAll() {
            super(PetOwner_persona.class);
        }
    }
}
