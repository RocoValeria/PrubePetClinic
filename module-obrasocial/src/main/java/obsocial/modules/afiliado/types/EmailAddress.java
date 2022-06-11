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
        maxLength = EmailAddress.MAX_LEN,
        optionality = Optionality.OPTIONAL,
        regexPattern = "[^@]+@[^@]+[.][^@]+",  // should really use https://emailregex.com/
        regexPatternReplacement = "Invalid email address"
)
@PropertyLayout(named = "E-mail")
@Parameter(maxLength = EmailAddress.MAX_LEN, optionality = Optionality.OPTIONAL)
@ParameterLayout(named = "E-mail")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddress {

    int MAX_LEN = 100;
}
