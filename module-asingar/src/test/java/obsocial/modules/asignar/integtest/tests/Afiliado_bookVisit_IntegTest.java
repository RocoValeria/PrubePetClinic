package obsocial.modules.afiliado.integtest.tests;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.apache.isis.applib.services.clock.ClockService;
import org.apache.isis.applib.services.wrapper.DisabledException;
import org.apache.isis.applib.services.wrapper.InvalidException;
import org.apache.isis.testing.fakedata.applib.services.FakeDataService;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.fixture.afiliado.Afiliado_persona;
import obsocial.modules.asignar.contributions.afiliado.Afiliado_bookVisit;
import obsocial.modules.asignar.dom.asignar.Asignar;
import obsocial.modules.asignar.dom.asignar.VisitRepository;
import obsocial.modules.asignar.integtest.VisitsModuleIntegTestAbstract;

public class Afiliado_bookVisit_IntegTest extends VisitsModuleIntegTestAbstract {

    @BeforeEach
    void setup() {
        fixtureScripts.run(new Afiliado_persona.PersistAll());
    }

    @Test
    public void happy_case() {

        // given
        Afiliado someAfiliado = fakeDataService.enums().anyOf(Afiliado_persona.class)
                        .findUsing(serviceRegistry);
        List<Asignar> before = asignarRepository.findByAfiliadoOrderByAsignarAtDesc(someAfiliado);

        // when
        LocalDateTime asignarAt = clockService.getClock().nowAsLocalDateTime()
                                    .plusDays(fakeDataService.ints().between(1, 3));
        String reason = fakeDataService.strings().upper(40);

        wrapMixin(Afiliado_bookVisit.class, someAfiliado).act(asignarAt, reason);

        // then
        List<Asignar> after = visitRepository.findByPlanByAsignarAtDesc(someAfiliado);
        after.removeAll(before);
        assertThat(after).hasSize(1);
        Asignar asignar = after.get(0);

        assertThat(asignar.getPet()).isSameAs(someAfiliado);
        assertThat(asignar.getAsignarAt()).isEqualTo(asignarAt);
        assertThat(asignar.getReason()).isEqualTo(reason);
    }

    @Test
    public void reason_is_required() {

        // given
        Pet somePet = fakeDataService.enums().anyOf(Pet_persona.class)
                        .findUsing(serviceRegistry);
        List<Visit> before = visitRepository.findByPetOrderByVisitAtDesc(somePet);

        // when, then
        LocalDateTime visitAt = clockService.getClock().nowAsLocalDateTime()
                                    .plusDays(fakeDataService.ints().between(1, 3));

        assertThatThrownBy(() ->
            wrapMixin(Pet_bookVisit.class, somePet).act(visitAt, null)
        )
        .isInstanceOf(InvalidException.class)
        .hasMessage("'Reason' is mandatory");
    }

    @Test
    public void cannot_book_in_the_past() {

        // given
        Pet somePet = fakeDataService.enums().anyOf(Pet_persona.class)
                .findUsing(serviceRegistry);
        List<Visit> before = visitRepository.findByPetOrderByVisitAtDesc(somePet);

        // when, then
        LocalDateTime visitAt = clockService.getClock().nowAsLocalDateTime();
        String reason = fakeDataService.strings().upper(40);

        assertThatThrownBy(() ->
                wrapMixin(Pet_bookVisit.class, somePet).act(visitAt, reason)
        )
                .isInstanceOf(InvalidException.class)
                .hasMessage("Must be in the future");
    }

    @Inject FakeDataService fakeDataService;
    @Inject VisitRepository visitRepository;
    @Inject ClockService clockService;

}
