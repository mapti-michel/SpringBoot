package br.acc.webflux.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { TrimStringValidator.class }) // Diz que é uma restrição
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) //Elemento em tempo de execução
public @interface TrimString {

    String message() default "Field cannot have blank spaces at the beginning or at end";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
