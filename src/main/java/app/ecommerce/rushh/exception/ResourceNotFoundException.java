package app.ecommerce.rushh.exception;

//public class ResourceNotFoundException extends RuntimeException {
//
//    public ResourceNotFoundException(String message) {
//        super(message);
//    }
//}

public class ResourceNotFoundException extends ApiException {
	
    public ResourceNotFoundException(String message) {
        super(message, "PRODUCT_NOT_FOUND");
    }
    
}

