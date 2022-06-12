package obsocial.modules.asignar.dom.asignar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import obsocial.modules.afiliado.dom.afiliado.Afiliado;
import obsocial.modules.afiliado.dom.afiliado.TipoPlan;
import obsocial.modules.afiliado.dom.plan.Plan;
import obsocial.modules.afiliado.types.Apellido;
import obsocial.modules.afiliado.types.Notas;
import obsocial.modules.afiliado.types.AfiliadoName;
import obsocial.modules.asignar.types.Reason;


@Entity
@Table(
    schema="mostrar",
    name = "Asignar",
    uniqueConstraints = {
        @UniqueConstraint(name = "Asignar__afiliado_asignarAt__UNQ", columnNames = {"plan_id", "name"})
    }
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "mostrar.Asignar", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Asignar implements Comparable<Visit> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "metadata", sequence = "1")
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    @PropertyLayout(fieldSetId = "metadata", sequence = "999")
    @Getter @Setter
    private long version;


    public Asignar(Afiliado afiliado, LocalDateTime asignarAt, String reason) {
        this.afiliado = afiliado;
        this.asignarAt = asignarAt;
        this.reason = reason;
    }


    public String title() {
        return titleService.titleOf(getAfiliado()) + " @ " + getAsignarAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "afiliado_id")
    @PropertyLayout(fieldSetId = "name", sequence = "1")
    @Getter @Setter
    private Afiliado afiliado;

    @Column(name = "asignarAt", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "name", sequence = "2")
    private LocalDateTime asignarAt;

    @Reason
    @Column(name = "reason", length = Nombre.MAX_LEN, nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "detalles", sequence = "1")
    private String reason;

    private final static Comparator<Asignar> comparator =
            Comparator.comparing(Asignar::getAfiliado).thenComparing(Asignar::getAsignarAt);

    @Override
    public int compareTo(final Asignar other) {
        return comparator.compare(this, other);
    }

    @Inject @Transient TitleService titleService;
}
