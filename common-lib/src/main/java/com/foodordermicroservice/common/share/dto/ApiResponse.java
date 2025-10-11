package com.foodordermicroservice.common.share.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Map<String, String> errors;
    private int statusCode;
    
    public static <T> ApiResponse<T> success(String message, T data, int statusCode) {
        return new ApiResponse<>(true, message, data, null, statusCode);
    }
    public static <T> ApiResponse<T> error(String message, Map<String, String> errors, int statusCode) {
        return new ApiResponse<>(false, message, null, errors, statusCode);
    }
    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(true, message, data, null, 201);
    }
    public static <T> ApiResponse<T> noContent(String message) {
        return new ApiResponse<>(true, message, null, null, 204);
    }
    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(true, message, null, null, 404);
    }
    public static <T> ApiResponse<T> forBidden(String message) {
        return new ApiResponse<>(true, message, null, null, 403);
    }
    public static <T> ApiResponse<T> unAuthorized(String message) {
        return new ApiResponse<>(true, message, null, null, 401);
    }
}
