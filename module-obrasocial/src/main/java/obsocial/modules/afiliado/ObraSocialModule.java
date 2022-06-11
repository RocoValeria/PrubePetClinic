package obsocial.modules.afiliado;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.modules.ModuleWithFixtures;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.plan.Plan;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EntityScan(basePackageClasses = {obrasocialModule.class})
public class ObraSocialModule implements ModuleWithFixtures {

    @Override
    public FixtureScript getTeardownFixture() {
        return new FixtureScript() {
            @Override
            protected void execute(ExecutionContext executionContext) {
                repositoryService.removeAll(Afiliado.class);
                repositoryService.removeAll(Plan.class);
            }
        };
    }
}
