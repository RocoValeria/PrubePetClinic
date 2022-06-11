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
        maxLength = PhoneNumber.MAX_LEN,
        optionality = Optionality.OPTIONAL,
        regexPattern = "[+]?[0-9 ]+",
        regexPatternReplacement =
                "Specify only numbers and spaces, optionally prefixed with '+'.  " +
                        "For example, '+353 1 555 1234', or '07123 456789'"
)
@Parameter(maxLength = PhoneNumber.MAX_LEN, optionality = Optionality.OPTIONAL)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    int MAX_LEN = 30;
}
