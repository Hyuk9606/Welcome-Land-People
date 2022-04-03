package com.ssafy.boonmoja.utils;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

public class ApiUtils {
    
    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }
    
    public static ApiResult<?> error(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(throwable, status));
    }
    
    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(message, status));
    }
    
    public static class ApiError {
        
        @Schema(description = "에러 메세지")
        private final String message;
        @Schema(description = "HTTP 상태 코드")
        private final int status;
        
        ApiError(Throwable throwable, HttpStatus status) {
            this(throwable.getMessage(), status);
        }
        
        ApiError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
        }
        
        public String getMessage() {
            return message;
        }
        
        public int getStatus() {
            return status;
        }
        
        
    }
    
    public static class ApiResult<T> {
        @Schema(description = "API 호출 성공 유무")
        private final boolean success;
        
        @Schema(description = "응답 데이터")
        private final T response;
        @Schema(description = "에러 정보")
        private final ApiError error;
        
        private ApiResult(boolean success, T response, ApiError error) {
            this.success = success;
            this.response = response;
            this.error = error;
        }
        
        public boolean isSuccess() {
            return success;
        }
        public ApiError getError() {
            return error;
        }
        public T getResponse() {
            return response;
        }
    }
}
