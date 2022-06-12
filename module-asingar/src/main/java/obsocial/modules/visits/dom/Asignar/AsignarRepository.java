package obsocial.modules.Asignar.dom.asignar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;

public interface AsignarRepository extends JpaRepository<Asignar, Long> {

    List<Asignar> findByPlanByAsignarAtDesc(Afiliado afiliado);

}
