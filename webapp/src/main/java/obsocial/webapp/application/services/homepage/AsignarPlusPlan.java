package obsocial.webapp.application.services.homepage;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.Projecting;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;

import obsocial.modules.afiliados.dom.afiliado.Afiliado;
import obsocial.modules.afiliados.dom.plan.Plan;
import obsocial.modules.asignar.dom.asignar.Asignar;

@DomainObject(nature=Nature.VIEW_MODEL, logicalTypeName = "obsocial.AsignarPlusPlan")
@DomainObjectLayout(named = "Asignar")
@XmlRootElement
@NoArgsConstructor
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class AsignarPlusPlan {

    @Property(
            projecting = Projecting.PROJECTED,
            hidden = Where.EVERYWHERE
    )
    @Getter
    private Asignar asignar;

    AsignarPlusPlan(Asignar asignar) {this.asignar = asignar;}

    public Afiliado getAfiliado() {return asignar.getAfiliado();}
    public String getReason() {return asignar.getReason();}
    public LocalDateTime getAsignarAt() {return asignar.getAsignarAt();}

    public Plan getPlan() {
        return getAfiliado().getPlan();
    }
}
