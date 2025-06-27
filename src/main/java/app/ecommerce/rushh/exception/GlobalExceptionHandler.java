package app.ecommerce.rushh.exception;

import app.ecommerce.rushh.payload.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ApiError buildError(HttpStatus status, String message, String errorCode, String path) {
        String traceId = UUID.randomUUID().toString();
        log.error("[{}] {}: {}", traceId, errorCode, message); // 🔥 Structured Logging
        return new ApiError(
                status.value(),
                status.getReasonPhrase(),
                message,
                errorCode,
                path,
                traceId
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiError error = buildError(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getErrorCode(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        ApiError error = buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getErrorCode(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleGenericApiException(ApiException ex, HttpServletRequest request) {
        ApiError error = buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getErrorCode(), request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalException(Exception ex, HttpServletRequest request) {
        ApiError error = buildError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "INTERNAL_ERROR", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

