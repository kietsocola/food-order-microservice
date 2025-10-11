package com.foodordermicroservice.common.utils;


import lombok.experimental.UtilityClass;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import com.foodordermicroservice.common.share.exceptions.ValidationException;

/**
 * Utility class for Validation operations
 */
@UtilityClass
public class ValidationUtils {

    // Regex patterns
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    private static final Pattern PHONE_VN_PATTERN = 
        Pattern.compile("^(0|\\+84)(\\d{9,10})$");
    
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9._-]{3,20}$");
    
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    // ==================== NOT NULL VALIDATION ====================

    /**
     * Validate object không null
     */
    public static <T> T requireNonNull(T obj, String fieldName) {
        if (obj == null) {
            throw new ValidationException(fieldName + " không được để trống");
        }
        return obj;
    }

    /**
     * Validate object không null với custom message
     */
    public static <T> T requireNonNull(T obj, String fieldName, String message) {
        if (obj == null) {
            throw new ValidationException(message);
        }
        return obj;
    }

    /**
     * Validate object không null với custom exception
     */
    public static <T> T requireNonNull(T obj, Supplier<? extends RuntimeException> exceptionSupplier) {
        if (obj == null) {
            throw exceptionSupplier.get();
        }
        return obj;
    }

    // ==================== STRING VALIDATION ====================

    /**
     * Validate string không null và không empty
     */
    public static String requireNonEmpty(String str, String fieldName) {
        if (StringUtils.isEmpty(str)) {
            throw new ValidationException(fieldName + " không được để trống");
        }
        return str;
    }

    /**
     * Validate string không blank
     */
    public static String requireNonBlank(String str, String fieldName) {
        if (StringUtils.isBlank(str)) {
            throw new ValidationException(fieldName + " không được để trống");
        }
        return str;
    }

    /**
     * Validate string length
     */
    public static String validateLength(String str, String fieldName, int min, int max) {
        requireNonNull(str, fieldName);
        int length = str.length();
        if (length < min || length > max) {
            throw new ValidationException(
                fieldName + " phải có độ dài từ " + min + " đến " + max + " ký tự"
            );
        }
        return str;
    }

    /**
     * Validate string min length
     */
    public static String validateMinLength(String str, String fieldName, int min) {
        requireNonNull(str, fieldName);
        if (str.length() < min) {
            throw new ValidationException(
                fieldName + " phải có ít nhất " + min + " ký tự"
            );
        }
        return str;
    }

    /**
     * Validate string max length
     */
    public static String validateMaxLength(String str, String fieldName, int max) {
        requireNonNull(str, fieldName);
        if (str.length() > max) {
            throw new ValidationException(
                fieldName + " không được vượt quá " + max + " ký tự"
            );
        }
        return str;
    }

    // ==================== EMAIL VALIDATION ====================

    /**
     * Validate email format
     */
    public static String validateEmail(String email, String fieldName) {
        requireNonEmpty(email, fieldName);
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException(fieldName + " không đúng định dạng email");
        }
        return email;
    }

    /**
     * Validate email format (sử dụng fieldName mặc định)
     */
    public static String validateEmail(String email) {
        return validateEmail(email, "Email");
    }

    // ==================== PHONE VALIDATION ====================

    /**
     * Validate số điện thoại Việt Nam
     */
    public static String validateVietnamesePhone(String phone, String fieldName) {
        requireNonEmpty(phone, fieldName);
        String cleanPhone = phone.replaceAll("[\\s-]", "");
        if (!PHONE_VN_PATTERN.matcher(cleanPhone).matches()) {
            throw new ValidationException(fieldName + " không đúng định dạng số điện thoại Việt Nam");
        }
        return phone;
    }

    /**
     * Validate số điện thoại Việt Nam (sử dụng fieldName mặc định)
     */
    public static String validateVietnamesePhone(String phone) {
        return validateVietnamesePhone(phone, "Số điện thoại");
    }

    // ==================== USERNAME & PASSWORD VALIDATION ====================

    /**
     * Validate username
     * - Từ 3-20 ký tự
     * - Chỉ chứa chữ, số, dấu chấm, gạch dưới, gạch ngang
     */
    public static String validateUsername(String username) {
        requireNonEmpty(username, "Username");
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new ValidationException(
                "Username phải từ 3-20 ký tự và chỉ chứa chữ, số, dấu chấm, gạch dưới, gạch ngang"
            );
        }
        return username;
    }

    /**
     * Validate password mạnh
     * - Ít nhất 8 ký tự
     * - Ít nhất 1 chữ hoa
     * - Ít nhất 1 chữ thường
     * - Ít nhất 1 số
     * - Ít nhất 1 ký tự đặc biệt
     */
    public static String validateStrongPassword(String password) {
        requireNonEmpty(password, "Password");
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new ValidationException(
                "Password phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt"
            );
        }
        return password;
    }

    /**
     * Validate password đơn giản (chỉ kiểm tra độ dài)
     */
    public static String validatePassword(String password, int minLength) {
        requireNonEmpty(password, "Password");
        if (password.length() < minLength) {
            throw new ValidationException("Password phải có ít nhất " + minLength + " ký tự");
        }
        return password;
    }

    // ==================== NUMBER VALIDATION ====================

    /**
     * Validate số nằm trong khoảng min-max
     */
    public static <T extends Number & Comparable<T>> T validateRange(
            T value, String fieldName, T min, T max) {
        requireNonNull(value, fieldName);
        if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
            throw new ValidationException(
                fieldName + " phải nằm trong khoảng từ " + min + " đến " + max
            );
        }
        return value;
    }

    /**
     * Validate số lớn hơn hoặc bằng min
     */
    public static <T extends Number & Comparable<T>> T validateMin(
            T value, String fieldName, T min) {
        requireNonNull(value, fieldName);
        if (value.compareTo(min) < 0) {
            throw new ValidationException(fieldName + " phải lớn hơn hoặc bằng " + min);
        }
        return value;
    }

    /**
     * Validate số nhỏ hơn hoặc bằng max
     */
    public static <T extends Number & Comparable<T>> T validateMax(
            T value, String fieldName, T max) {
        requireNonNull(value, fieldName);
        if (value.compareTo(max) > 0) {
            throw new ValidationException(fieldName + " phải nhỏ hơn hoặc bằng " + max);
        }
        return value;
    }

    /**
     * Validate số dương
     */
    public static <T extends Number & Comparable<T>> T validatePositive(
            T value, String fieldName) {
        requireNonNull(value, fieldName);
        if (value.doubleValue() <= 0) {
            throw new ValidationException(fieldName + " phải là số dương");
        }
        return value;
    }

    /**
     * Validate số không âm
     */
    public static <T extends Number & Comparable<T>> T validateNonNegative(
            T value, String fieldName) {
        requireNonNull(value, fieldName);
        if (value.doubleValue() < 0) {
            throw new ValidationException(fieldName + " không được là số âm");
        }
        return value;
    }

    // ==================== COLLECTION VALIDATION ====================

    /**
     * Validate collection không null và không empty
     */
    public static <T extends Collection<?>> T requireNonEmpty(T collection, String fieldName) {
        requireNonNull(collection, fieldName);
        if (collection.isEmpty()) {
            throw new ValidationException(fieldName + " không được để trống");
        }
        return collection;
    }

    /**
     * Validate collection size
     */
    public static <T extends Collection<?>> T validateSize(
            T collection, String fieldName, int min, int max) {
        requireNonNull(collection, fieldName);
        int size = collection.size();
        if (size < min || size > max) {
            throw new ValidationException(
                fieldName + " phải có từ " + min + " đến " + max + " phần tử"
            );
        }
        return collection;
    }

    // ==================== MAP VALIDATION ====================

    /**
     * Validate map không null và không empty
     */
    public static <T extends Map<?, ?>> T requireNonEmpty(T map, String fieldName) {
        requireNonNull(map, fieldName);
        if (map.isEmpty()) {
            throw new ValidationException(fieldName + " không được để trống");
        }
        return map;
    }

    // ==================== BOOLEAN VALIDATION ====================

    /**
     * Validate điều kiện phải đúng
     */
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new ValidationException(message);
        }
    }

    /**
     * Validate điều kiện phải sai
     */
    public static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new ValidationException(message);
        }
    }

    // ==================== CUSTOM PATTERN VALIDATION ====================

    /**
     * Validate string theo regex pattern
     */
    public static String validatePattern(String str, String fieldName, Pattern pattern, String message) {
        requireNonEmpty(str, fieldName);
        if (!pattern.matcher(str).matches()) {
            throw new ValidationException(message);
        }
        return str;
    }

    /**
     * Validate string theo regex pattern string
     */
    public static String validatePattern(String str, String fieldName, String regex, String message) {
        return validatePattern(str, fieldName, Pattern.compile(regex), message);
    }

    // ==================== DATE VALIDATION ====================

    /**
     * Validate ngày trong quá khứ
     */
    public static void validatePastDate(java.time.LocalDate date, String fieldName) {
        requireNonNull(date, fieldName);
        if (!DateUtils.isPast(date)) {
            throw new ValidationException(fieldName + " phải là ngày trong quá khứ");
        }
    }

    /**
     * Validate ngày trong tương lai
     */
    public static void validateFutureDate(java.time.LocalDate date, String fieldName) {
        requireNonNull(date, fieldName);
        if (!DateUtils.isFuture(date)) {
            throw new ValidationException(fieldName + " phải là ngày trong tương lai");
        }
    }
}