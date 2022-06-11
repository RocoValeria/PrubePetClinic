package obsocial.modules.afiliado.dom.afiliado;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import obsocial.modules.afiliado.dom.plan.Plan;

public interface AfiliadoRepository extends JpaRepository<Afiliado, Long> {

    List<Afiliado> findByPlan(Plan plan);
    Optional<Afiliado> findByPetOwnerAndName(Plan plan, String nombre);
}
