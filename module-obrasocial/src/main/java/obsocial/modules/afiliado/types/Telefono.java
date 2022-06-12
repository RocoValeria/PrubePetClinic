package petclinic.modules.pets.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;

@Property(
        editing = Editing.ENABLED,
        maxLength = Telefono.MAX_LEN,
        optionality = Optionality.OPTIONAL,
        regexPattern = "[+]?[0-9 ]+",
        regexPatternReplacement =
                "Se admite solo numero y espacios, opcionalmente '+'.  " +
                        "Por Ejemplo, '+353 1 555 1234', or '07123 456789'"
)
@Parameter(maxLength = Telefono.MAX_LEN, optionality = Optionality.OPTIONAL)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Telefono {

    int MAX_LEN = 30;
}
