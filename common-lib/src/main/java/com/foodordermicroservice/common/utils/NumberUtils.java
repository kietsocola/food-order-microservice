package com.foodordermicroservice.common.utils;

import lombok.experimental.UtilityClass;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Utility class for Number operations
 */
@UtilityClass
public class NumberUtils {

    private static final DecimalFormat CURRENCY_VN_FORMAT = 
        new DecimalFormat("#,### VNĐ");
    
    private static final DecimalFormat NUMBER_FORMAT = 
        new DecimalFormat("#,###.##");

    /**
     * Kiểm tra số null hoặc zero
     */
    public static boolean isNullOrZero(Number number) {
        return number == null || number.doubleValue() == 0;
    }

    /**
     * Kiểm tra số không null và khác zero
     */
    public static boolean isNotNullOrZero(Number number) {
        return !isNullOrZero(number);
    }

    /**
     * Kiểm tra số dương
     */
    public static boolean isPositive(Number number) {
        return number != null && number.doubleValue() > 0;
    }

    /**
     * Kiểm tra số âm
     */
    public static boolean isNegative(Number number) {
        return number != null && number.doubleValue() < 0;
    }

    /**
     * Safe parse Integer từ String
     */
    public static Integer toInteger(String str) {
        return toInteger(str, null);
    }

    /**
     * Safe parse Integer từ String với giá trị mặc định
     */
    public static Integer toInteger(String str, Integer defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Safe parse Long từ String
     */
    public static Long toLong(String str) {
        return toLong(str, null);
    }

    /**
     * Safe parse Long từ String với giá trị mặc định
     */
    public static Long toLong(String str, Long defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Safe parse Double từ String
     */
    public static Double toDouble(String str) {
        return toDouble(str, null);
    }

    /**
     * Safe parse Double từ String với giá trị mặc định
     */
    public static Double toDouble(String str, Double defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Safe parse BigDecimal từ String
     */
    public static BigDecimal toBigDecimal(String str) {
        return toBigDecimal(str, null);
    }

    /**
     * Safe parse BigDecimal từ String với giá trị mặc định
     */
    public static BigDecimal toBigDecimal(String str, BigDecimal defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        try {
            return new BigDecimal(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Làm tròn số với số chữ số thập phân
     */
    public static double round(double value, int decimals) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimals, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Làm tròn BigDecimal
     */
    public static BigDecimal round(BigDecimal value, int decimals) {
        if (value == null) {
            return null;
        }
        return value.setScale(decimals, RoundingMode.HALF_UP);
    }

    /**
     * Làm tròn lên
     */
    public static double roundUp(double value, int decimals) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimals, RoundingMode.UP);
        return bd.doubleValue();
    }

    /**
     * Làm tròn xuống
     */
    public static double roundDown(double value, int decimals) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimals, RoundingMode.DOWN);
        return bd.doubleValue();
    }

    /**
     * Format số thành currency VNĐ
     * Ví dụ: 1000000 -> "1,000,000 VNĐ"
     */
    public static String formatCurrencyVN(Number number) {
        if (number == null) {
            return "0 VNĐ";
        }
        return CURRENCY_VN_FORMAT.format(number);
    }

    /**
     * Format số với dấu phẩy ngăn cách
     * Ví dụ: 1000000 -> "1,000,000"
     */
    public static String formatNumber(Number number) {
        if (number == null) {
            return "0";
        }
        return NUMBER_FORMAT.format(number);
    }

    /**
     * Format số với pattern tùy chỉnh
     */
    public static String format(Number number, String pattern) {
        if (number == null) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(number);
    }

    /**
     * Format phần trăm
     * Ví dụ: 0.15 -> "15%"
     */
    public static String formatPercentage(double value) {
        return Math.round(value * 100) + "%";
    }

    /**
     * Format phần trăm với số chữ số thập phân
     * Ví dụ: 0.1567, 2 -> "15.67%"
     */
    public static String formatPercentage(double value, int decimals) {
        double percentage = value * 100;
        return round(percentage, decimals) + "%";
    }

    /**
     * Tính phần trăm
     * Ví dụ: percentage(25, 100) -> 25.0
     */
    public static double percentage(Number value, Number total) {
        if (isNullOrZero(total)) {
            return 0;
        }
        return (value.doubleValue() / total.doubleValue()) * 100;
    }

    /**
     * Tính giá trị từ phần trăm
     * Ví dụ: percentageOf(25, 100) -> 25.0
     */
    public static double percentageOf(double percentage, Number total) {
        if (total == null) {
            return 0;
        }
        return (percentage / 100) * total.doubleValue();
    }

    /**
     * Tính tỷ lệ tăng trưởng (%)
     * Ví dụ: growthRate(100, 150) -> 50.0
     */
    public static double growthRate(Number oldValue, Number newValue) {
        if (isNullOrZero(oldValue)) {
            return 0;
        }
        return ((newValue.doubleValue() - oldValue.doubleValue()) / oldValue.doubleValue()) * 100;
    }

    /**
     * So sánh 2 số với epsilon (cho số thực)
     */
    public static boolean equals(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }

    /**
     * So sánh 2 số với epsilon mặc định 0.000001
     */
    public static boolean equals(double a, double b) {
        return equals(a, b, 0.000001);
    }

    /**
     * Lấy giá trị lớn nhất
     */
    public static <T extends Number & Comparable<T>> T max(T a, T b) {
        if (a == null) return b;
        if (b == null) return a;
        return a.compareTo(b) > 0 ? a : b;
    }

    /**
     * Lấy giá trị nhỏ nhất
     */
    public static <T extends Number & Comparable<T>> T min(T a, T b) {
        if (a == null) return b;
        if (b == null) return a;
        return a.compareTo(b) < 0 ? a : b;
    }

    /**
     * Clamp giá trị trong khoảng min-max
     */
    public static <T extends Number & Comparable<T>> T clamp(T value, T min, T max) {
        if (value == null) return min;
        if (value.compareTo(min) < 0) return min;
        if (value.compareTo(max) > 0) return max;
        return value;
    }

    /**
     * Kiểm tra số nằm trong khoảng
     */
    public static <T extends Number & Comparable<T>> boolean inRange(T value, T min, T max) {
        if (value == null) return false;
        return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
    }

    /**
     * Tính tổng của mảng số
     */
    public static double sum(Number... numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        double sum = 0;
        for (Number number : numbers) {
            if (number != null) {
                sum += number.doubleValue();
            }
        }
        return sum;
    }

    /**
     * Tính trung bình của mảng số
     */
    public static double average(Number... numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        return sum(numbers) / numbers.length;
    }

    /**
     * Convert bytes sang dạng dễ đọc
     * Ví dụ: 1024 -> "1 KB", 1048576 -> "1 MB"
     */
    public static String formatBytes(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String[] units = {"B", "KB", "MB", "GB", "TB", "PB"};
        return String.format("%.2f %s", bytes / Math.pow(1024, exp), units[exp]);
    }

    /**
     * Tính lũy thừa
     */
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Tính căn bậc 2
     */
    public static double sqrt(double value) {
        return Math.sqrt(value);
    }

    /**
     * Tính giá trị tuyệt đối
     */
    public static double abs(double value) {
        return Math.abs(value);
    }

    /**
     * Random số nguyên trong khoảng [min, max]
     */
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /**
     * Random số thực trong khoảng [min, max]
     */
    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    /**
     * Kiểm tra số chẵn
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Kiểm tra số lẻ
     */
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    /**
     * Tính giai thừa
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n phải >= 0");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Kiểm tra số nguyên tố
     */
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tính ước chung lớn nhất (GCD)
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    /**
     * Tính bội chung nhỏ nhất (LCM)
     */
    public static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    /**
     * Convert số La Mã sang số nguyên
     */
    public static int romanToInt(String roman) {
        if (StringUtils.isEmpty(roman)) {
            return 0;
        }
        
        int result = 0;
        int prevValue = 0;
        
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = switch (roman.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> 0;
            };
            
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        
        return result;
    }
}