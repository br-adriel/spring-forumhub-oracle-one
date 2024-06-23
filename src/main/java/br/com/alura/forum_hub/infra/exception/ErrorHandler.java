package br.com.alura.forum_hub.infra.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DadosErroValidacao>> handleError400(MethodArgumentNotValidException ex) {
		var errors = ex.getFieldErrors();
		return ResponseEntity.badRequest()
				.body(errors.stream().map(DadosErroValidacao::new).toList());
	}
}
