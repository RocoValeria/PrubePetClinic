package obsocial.modules.afiliado.dom.plan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import obsocial.modules.afiliado.dom.plan.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByApellidoContaining(final String nombre);

    Plan findByApellido(final String nombre);

}
