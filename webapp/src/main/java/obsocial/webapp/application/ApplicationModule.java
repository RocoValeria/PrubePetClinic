package obsocial.webapp.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import obsocial.modules.mostrar.MostrarModule;

@Configuration
@Import(MostrarModule.class)
@ComponentScan
public class ApplicationModule {

}
