package io.github.teixeira007.Vendas.validation;

import io.github.teixeira007.Vendas.validation.constraintvalidation.NotEmptyListValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidation.class)
public @interface NotEmptyList {
    String message() default "Lista não pode ser vazia";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
