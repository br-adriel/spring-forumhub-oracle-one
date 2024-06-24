package br.com.alura.forum_hub.validation.email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EmailNaoUtilizadoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNaoUtilizado {
	String message()

	default "o email já está associado a um usuário";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}