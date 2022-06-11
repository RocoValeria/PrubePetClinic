package petclinic.modules.Afiliados.dom.afiliados;

import java.util.Comparator;

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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.jaxb.PersistentEntityAdapter;
import org.apache.isis.persistence.jpa.applib.integration.IsisEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import petclinic.modules.pets.dom.petowner.PetOwner;
import petclinic.modules.pets.types.FirstName;
import petclinic.modules.pets.types.Notes;
import petclinic.modules.pets.types.PetName;


@Entity
@Table(
    schema="afiliados",
    name = "afiliado",
    uniqueConstraints = {
        @UniqueConstraint(name = "Afiliado__plan_nombre__UNQ", columnNames = {"plan_id", "nombre"})
    }
)
@EntityListeners(IsisEntityListener.class)
@DomainObject(logicalTypeName = "afiliados.Afiliado", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@XmlJavaTypeAdapter(PersistentEntityAdapter.class)
@ToString(onlyExplicitlyIncluded = true)
public class Afiliado implements Comparable<Pet> {

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


    Afiliado(Plan plan, String name, TipoPlan tipoPlan) {
        this.plan = plan;
        this.nombre = nombre;
        this.tipoPlan = tipoPlan;
    }


    public String title() {
        return getNombre() + " " + getPlan().getLastNombre();
    }

    public String iconName() {
        return getTipoPlan().nombre().toLowerCase();
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id")
    @PropertyLayout(fieldSetId = "nombre", sequence = "1")
    @Getter @Setter
    private Plan plan;

    @AfiliadoNombre
    @Column(name = "nombre", length = FirstName.MAX_LEN, nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "nombre", sequence = "2")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nombre = "TipoPlan", nullable = false)
    @Getter @Setter
    @PropertyLayout(fieldSetId = "details", sequence = "1")
    private TipoPlan tipoPlan;

    @Notas
    @Column(nombre = "notas", length = Notes.MAX_LEN, nullable = true)
    @Getter @Setter
    @Property(commandPublishing = Publishing.ENABLED, executionPublishing = Publishing.ENABLED)
    @PropertyLayout(fieldSetId = "notas", sequence = "1")
    private String notas;


    private final static Comparator<Afiliado> comparator =
            Comparator.comparing(Afiliado::getPlan).thenComparing(Afiliado::getNombre);

    @Override
    public int compareTo(final Afiliado other) {
        return comparator.compare(this, other);
    }

}
