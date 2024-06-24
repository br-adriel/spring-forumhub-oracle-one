package br.com.alura.forum_hub.validation.email;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.forum_hub.domain.usuario.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailNaoUtilizadoValidator implements ConstraintValidator<EmailNaoUtilizado, DtoCampoEmail> {
	@Autowired
	private UsuarioRepository repository;

	@Override
	public void initialize(EmailNaoUtilizado constraintAnnotation) {
	}

	@Override
	public boolean isValid(DtoCampoEmail dados, ConstraintValidatorContext context) {
		if (dados == null || dados.email() == null) {
			return true;
		}

		var userExists = repository.existsByEmail(dados.email());

		if (userExists) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
					.addPropertyNode("email")
					.addConstraintViolation();
		}

		return !userExists;
	}
}
