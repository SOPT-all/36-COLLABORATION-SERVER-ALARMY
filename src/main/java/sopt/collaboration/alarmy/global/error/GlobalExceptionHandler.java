package sopt.collaboration.alarmy.global.error;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import sopt.collaboration.alarmy.global.error.exception.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e){
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        ErrorCode errorCode = ErrorCode.NOT_SUPPORTED_METHOD_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e){
        ErrorCode errorCode = ErrorCode.NOT_FOUND_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        ErrorCode errorCode = ErrorCode.NOT_VALID_HEADER;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<ErrorResponse> handleFeignException(FeignException e){
        ErrorCode errorCode = ErrorCode.EXTERNAL_API_CLIENT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleInternalServerError(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }
}
