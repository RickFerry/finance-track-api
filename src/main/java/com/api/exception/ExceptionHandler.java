package com.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msgUsuario = messageSource.getMessage("message.invalid.request", null, LocaleContextHolder.getLocale());
        String msgDesenvolvedor = ex.getCause().toString();
        return handleExceptionInternal(ex, Collections.singletonList(new Erro(msgUsuario, msgDesenvolvedor)), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        ArrayList<Erro> erros = new ArrayList<>();
        for (ObjectError error : bindingResult.getFieldErrors()) {
            String msgUsuario = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            String msgDesenvolvedor = error.toString();
            erros.add(new Erro(msgUsuario, msgDesenvolvedor));
        }
        return erros;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Erro {
        private String msgUsuario;
        private String msgDesenvolvedor;
    }
}
