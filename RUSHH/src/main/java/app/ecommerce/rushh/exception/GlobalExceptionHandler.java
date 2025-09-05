//package app.ecommerce.rushh.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
//        return new ResponseEntity<>("Invalid request input: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException(Exception ex) {
//        return new ResponseEntity<>("Internal server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}


//package app.ecommerce.rushh.exception;
//
//import app.ecommerce.rushh.payload.ApiError;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
//        ApiError error = new ApiError(
//                HttpStatus.NOT_FOUND.value(),
//                "Not Found",
//                ex.getMessage(),
//                request.getRequestURI()
//        );
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
//        ApiError error = new ApiError(
//                HttpStatus.BAD_REQUEST.value(),
//                "Bad Request",
//                ex.getMessage(),
//                request.getRequestURI()
//        );
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
//        ApiError error = new ApiError(
//                HttpStatus.BAD_REQUEST.value(),
//                "Invalid Argument",
//                ex.getMessage(),
//                request.getRequestURI()
//        );
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiError> handleOtherExceptions(Exception ex, HttpServletRequest request) {
//        ApiError error = new ApiError(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Internal Server Error",
//                ex.getMessage(),
//                request.getRequestURI()
//        );
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}


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

