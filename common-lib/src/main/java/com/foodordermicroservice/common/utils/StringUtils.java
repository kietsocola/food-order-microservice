package com.foodordermicroservice.common.utils;

import lombok.experimental.UtilityClass;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Utility class for String operations
 */
@UtilityClass
public class StringUtils {

    /**
     * Kiểm tra chuỗi null hoặc empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Kiểm tra chuỗi không null và không empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Kiểm tra chuỗi null hoặc blank (chỉ chứa whitespace)
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Kiểm tra chuỗi không blank
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Chuyển chuỗi thành null nếu empty
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }

    /**
     * Chuyển null thành empty string
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * Viết hoa chữ cái đầu
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Viết hoa chữ cái đầu mỗi từ
     */
    public static String capitalizeWords(String str) {
        if (isEmpty(str)) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(capitalize(word)).append(" ");
            }
        }
        return result.toString().trim();
    }

    /**
     * Chuyển về dạng slug (URL friendly)
     * Ví dụ: "Hà Nội Việt Nam" -> "ha-noi-viet-nam"
     */
    public static String toSlug(String str) {
        if (isEmpty(str)) {
            return "";
        }
        
        // Normalize và loại bỏ dấu tiếng Việt
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutAccents = pattern.matcher(normalized).replaceAll("");
        
        // Chuyển thành lowercase và thay thế ký tự đặc biệt
        return withoutAccents.toLowerCase()
                .replaceAll("đ", "d")
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-")
                .replaceAll("^-|-$", "");
    }

    /**
     * Loại bỏ ký tự đặc biệt, chỉ giữ chữ và số
     */
    public static String removeSpecialCharacters(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    /**
     * Truncate chuỗi với độ dài tối đa
     */
    public static String truncate(String str, int maxLength) {
        return truncate(str, maxLength, "...");
    }

    /**
     * Truncate chuỗi với độ dài tối đa và suffix
     */
    public static String truncate(String str, int maxLength, String suffix) {
        if (isEmpty(str) || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - suffix.length()) + suffix;
    }

    /**
     * Mask email: example@gmail.com -> ex***@gmail.com
     */
    public static String maskEmail(String email) {
        if (isEmpty(email) || !email.contains("@")) {
            return email;
        }
        String[] parts = email.split("@");
        String username = parts[0];
        if (username.length() <= 2) {
            return email;
        }
        return username.substring(0, 2) + "***@" + parts[1];
    }

    /**
     * Mask phone: 0123456789 -> 012***6789
     */
    public static String maskPhone(String phone) {
        if (isEmpty(phone) || phone.length() < 8) {
            return phone;
        }
        int maskLength = phone.length() - 6;
        return phone.substring(0, 3) + "*".repeat(maskLength) + phone.substring(phone.length() - 4);
    }

    /**
     * Generate random alphanumeric string
     */
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            result.append(chars.charAt(index));
        }
        return result.toString();
    }

    /**
     * Generate random numeric string
     */
    public static String generateRandomNumeric(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append((int) (Math.random() * 10));
        }
        return result.toString();
    }

    /**
     * Kiểm tra email hợp lệ
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Kiểm tra số điện thoại Việt Nam hợp lệ
     */
    public static boolean isValidVietnamesePhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        // Regex cho SĐT Việt Nam: bắt đầu bằng 0, có 10-11 số
        String phoneRegex = "^(0|\\+84)(\\d{9,10})$";
        return phone.matches(phoneRegex);
    }

    /**
     * Format số điện thoại Việt Nam: 0123456789 -> 012 345 6789
     */
    public static String formatVietnamesePhone(String phone) {
        if (isEmpty(phone) || phone.length() < 10) {
            return phone;
        }
        phone = phone.replaceAll("[^0-9]", "");
        if (phone.length() == 10) {
            return phone.substring(0, 3) + " " + phone.substring(3, 6) + " " + phone.substring(6);
        }
        return phone;
    }

    /**
     * So sánh 2 chuỗi không phân biệt hoa thường
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * Join array thành string với delimiter
     */
    public static String join(String[] array, String delimiter) {
        if (array == null || array.length == 0) {
            return "";
        }
        return String.join(delimiter, array);
    }

    /**
     * Repeat string n lần
     */
    public static String repeat(String str, int times) {
        if (isEmpty(str) || times <= 0) {
            return "";
        }
        return str.repeat(times);
    }

    /**
     * Reverse string
     */
    public static String reverse(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Count occurrences of substring
     */
    public static int countOccurrences(String str, String substr) {
        if (isEmpty(str) || isEmpty(substr)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(substr, index)) != -1) {
            count++;
            index += substr.length();
        }
        return count;
    }
}