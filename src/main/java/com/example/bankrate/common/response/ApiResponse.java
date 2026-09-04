package com.example.bankrate.common.response;

import java.time.OffsetDateTime;

public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ErrorDetail error;
    private OffsetDateTime timestamp;

    public ApiResponse() {}

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTimestamp(OffsetDateTime.now());
        return response;
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setError(new ErrorDetail(code, message));
        response.setTimestamp(OffsetDateTime.now());
        return response;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public ErrorDetail getError() { return error; }
    public void setError(ErrorDetail error) { this.error = error; }
    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }

    public static class ErrorDetail {
        private String code;
        private String message;

        public ErrorDetail() {}
        
        public ErrorDetail(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
