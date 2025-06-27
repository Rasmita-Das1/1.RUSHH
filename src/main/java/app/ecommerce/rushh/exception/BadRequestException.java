package app.ecommerce.rushh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
//public class BadRequestException extends RuntimeException {
//
//    public BadRequestException(String message) {
//        super(message);
//    }
//}

public class BadRequestException extends ApiException {
	
    public BadRequestException(String message) {
        super(message, "INVALID_REQUEST");
    }
    
}

