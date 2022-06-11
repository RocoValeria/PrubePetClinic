package obsocial.modules.afiliado.dom.plan;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.persistence.jpa.applib.services.JpaSupportService;

import lombok.RequiredArgsConstructor;

import obsocial.modules.afiliado.types.Apellido;
import obsocial.modules.afiliado.types.Nombre;

@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "afiliados.Planes"
)
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject} )
public class Planes {

    final RepositoryService repositoryService;
    final JpaSupportService jpaSupportService;
    final PlanRepository planRepository;


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public Plan create(
            @Apellido final String apellido,
            @nombre final String nombre) {
        return repositoryService.persist(Plan.withName(apellido, nombre));
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    public List<Plan> findByApellidoLike(
            @Apellido final String apellido) {
        return repositoryService.allMatches(
                Query.named(Plan.class, Plan.NAMED_QUERY__FIND_BY_APELLIDO_LIKE)
                     .withParameter("apellido", "%" + apellido + "%"));
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Plan> findByApellido(
            @Apellido final String apellido
            ) {
        return planRepository.findByApellidoContaining(apellido);
    }


    @Programmatic
    public Plan findByApellidoExact(final String apellido) {
        return planRepository.findByApellido(apellido);
    }



    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
    public List<Plan> listAll() {
        return planRepository.findAll();
    }




    @Programmatic
    public void ping() {
        jpaSupportService.getEntityManager(Plan.class)
            .ifSuccess(entityManager -> {
                final TypedQuery<Plan> q = entityManager.createQuery(
                        "SELECT p FROM Plan p ORDER BY p.apellido",
                        Plan.class)
                    .setMaxResults(1);
                q.getResultList();
            });
    }


}
