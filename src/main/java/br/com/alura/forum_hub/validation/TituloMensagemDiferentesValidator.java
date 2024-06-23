package br.com.alura.forum_hub.validation;

import br.com.alura.forum_hub.domain.topico.DadosTopico;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TituloMensagemDiferentesValidator
		implements ConstraintValidator<TituloMensagemDiferentes, DadosTopico> {

	@Override
	public void initialize(TituloMensagemDiferentes constraintAnnotation) {
	}

	@Override
	public boolean isValid(DadosTopico dados, ConstraintValidatorContext context) {
		if (dados == null || dados.titulo() == null || dados.mensagem() == null) {
			return true;
		}

		boolean valid = !dados.titulo().equalsIgnoreCase(dados.mensagem());

		if (!valid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
					.addPropertyNode("titulo")
					.addConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
					.addPropertyNode("mensagem")
					.addConstraintViolation();
		}

		return valid;
	}
}
