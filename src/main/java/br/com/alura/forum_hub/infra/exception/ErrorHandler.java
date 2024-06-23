package br.com.alura.forum_hub.infra.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

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

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> handle404(EntityNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handle400(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
