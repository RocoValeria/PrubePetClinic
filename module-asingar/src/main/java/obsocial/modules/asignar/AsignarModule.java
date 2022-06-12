package obsocial.modules.asignar;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;

import obsocial.modules.pets.AfiliadosModule;
import obsocial.modules.pets.dom.plan.Plan;
import obsocial.modules.asignar.dom.asignar.Asignar;

@Configuration
@ComponentScan
@Import(AfiliadosModule.class)
@EnableJpaRepositories
@EntityScan(basePackageClasses = {AsignarModule.class})
public class AsignarModule implements ModuleWithFixtures {

    @Override
    public FixtureScript getTeardownFixture() {
        return new FixtureScript() {
            @Override
            protected void execute(ExecutionContext executionContext) {
                repositoryService.removeAll(Visit.class);
            }
        };
    }
}
