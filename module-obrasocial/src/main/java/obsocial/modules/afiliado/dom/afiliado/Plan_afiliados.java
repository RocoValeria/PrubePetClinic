package obsocial.modules.afiliado.dom.afiliado;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;

import lombok.RequiredArgsConstructor;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.afiliado.AfiliadoRepository;
import obsocial.modules.afiliado.dom.plan.Planr;

@Collection
@CollectionLayout(defaultView = "table")
@RequiredArgsConstructor
public class Plan_afiliados {

    private final Plan plan;

    public List<Afiliados> coll() {
        return afiliadoRepository.findByPlan(afiliado);
    }

    @Inject AfiliadoRepository afiliadoRepository;
}
