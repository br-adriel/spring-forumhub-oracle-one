package br.com.alura.forum_hub.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TituloMensagemDiferentesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TituloMensagemDiferentes {
	String message()

	default "o título e a mensagem devem ser diferentes";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
