package petclinic.modules.pets.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;

@Property(maxLength = FirstName.MAX_LEN, optionality = Optionality.OPTIONAL)
@Parameter(maxLength = FirstName.MAX_LEN, optionality = Optionality.OPTIONAL)
@ParameterLayout(named = "First Name")
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstName {

    int MAX_LEN = 40;
}
