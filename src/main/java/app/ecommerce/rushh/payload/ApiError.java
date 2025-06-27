//package app.ecommerce.rushh.payload;
//
//import java.time.LocalDateTime;
//
//public class ApiError {
//	
//    private LocalDateTime timestamp;
//    private int status;
//    private String error;
//    private String message;
//    private String path;
//
//    public ApiError(int status, String error, String message, String path) {
//        this.timestamp = LocalDateTime.now();
//        this.status = status;
//        this.error = error;
//        this.message = message;
//        this.path = path;
//    }
//
//    // Getters and Setters
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public String getError() {
//        return error;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public String getPath() {
//        return path;
//    }
//}

package app.ecommerce.rushh.payload;

import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String errorCode;
    private String path;
    private String traceId;

    public ApiError(int status, String error, String message, String errorCode, String path, String traceId) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.errorCode = errorCode;
        this.path = path;
        this.traceId = traceId;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getPath() {
        return path;
    }

    public String getTraceId() {
        return traceId;
    }
}

