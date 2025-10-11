package com.foodordermicroservice.common.utils;

import lombok.experimental.UtilityClass;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Utility class for Date and Time operations
 */
@UtilityClass
public class DateUtils {

    // Common date patterns
    public static final String PATTERN_DATE = "dd/MM/yyyy";
    public static final String PATTERN_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
    public static final String PATTERN_DATE_TIME_ISO = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String PATTERN_DATE_ISO = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_DATE_TIME_FULL = "dd/MM/yyyy HH:mm:ss.SSS";

    // Default timezone
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Ho_Chi_Minh");

    /**
     * Lấy thời gian hiện tại
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(DEFAULT_ZONE_ID);
    }

    /**
     * Lấy ngày hiện tại
     */
    public static LocalDate today() {
        return LocalDate.now(DEFAULT_ZONE_ID);
    }

    /**
     * Lấy ngày hôm qua
     */
    public static LocalDate yesterday() {
        return today().minusDays(1);
    }

    /**
     * Lấy ngày mai
     */
    public static LocalDate tomorrow() {
        return today().plusDays(1);
    }

    /**
     * Format LocalDateTime sang String
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Format LocalDate sang String
     */
    public static String format(LocalDate date, String pattern) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Format LocalDateTime với pattern mặc định
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return format(dateTime, PATTERN_DATE_TIME);
    }

    /**
     * Format LocalDate với pattern mặc định
     */
    public static String formatDate(LocalDate date) {
        return format(date, PATTERN_DATE);
    }

    /**
     * Parse String sang LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern) {
        if (StringUtils.isEmpty(dateTimeStr)) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Parse String sang LocalDate
     */
    public static LocalDate parseDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Parse String sang LocalDateTime với pattern mặc định
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return parseDateTime(dateTimeStr, PATTERN_DATE_TIME);
    }

    /**
     * Parse String sang LocalDate với pattern mặc định
     */
    public static LocalDate parseDate(String dateStr) {
        return parseDate(dateStr, PATTERN_DATE);
    }

    /**
     * Convert Date sang LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_ID);
    }

    /**
     * Convert LocalDateTime sang Date
     */
    public static Date toDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return Date.from(dateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * Convert LocalDate sang Date
     */
    public static Date toDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return Date.from(date.atStartOfDay(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * Lấy timestamp hiện tại (milliseconds)
     */
    public static long currentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * Convert LocalDateTime sang timestamp
     */
    public static long toTimestamp(LocalDateTime dateTime) {
        if (dateTime == null) {
            return 0;
        }
        return dateTime.atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
    }

    /**
     * Convert timestamp sang LocalDateTime
     */
    public static LocalDateTime fromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), DEFAULT_ZONE_ID);
    }

    /**
     * Tính số ngày giữa 2 ngày
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Tính số giờ giữa 2 thời điểm
     */
    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * Tính số phút giữa 2 thời điểm
     */
    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return 0;
        }
        return ChronoUnit.MINUTES.between(start, end);
    }

    /**
     * Kiểm tra ngày có phải hôm nay không
     */
    public static boolean isToday(LocalDate date) {
        return date != null && date.equals(today());
    }

    /**
     * Kiểm tra ngày có phải quá khứ không
     */
    public static boolean isPast(LocalDate date) {
        return date != null && date.isBefore(today());
    }

    /**
     * Kiểm tra ngày có phải tương lai không
     */
    public static boolean isFuture(LocalDate date) {
        return date != null && date.isAfter(today());
    }

    /**
     * Kiểm tra thời điểm có phải quá khứ không
     */
    public static boolean isPast(LocalDateTime dateTime) {
        return dateTime != null && dateTime.isBefore(now());
    }

    /**
     * Kiểm tra thời điểm có phải tương lai không
     */
    public static boolean isFuture(LocalDateTime dateTime) {
        return dateTime != null && dateTime.isAfter(now());
    }

    /**
     * Lấy ngày đầu tháng
     */
    public static LocalDate getFirstDayOfMonth(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.withDayOfMonth(1);
    }

    /**
     * Lấy ngày cuối tháng
     */
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    /**
     * Lấy ngày đầu tuần (Thứ 2)
     */
    public static LocalDate getFirstDayOfWeek(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.with(DayOfWeek.MONDAY);
    }

    /**
     * Lấy ngày cuối tuần (Chủ nhật)
     */
    public static LocalDate getLastDayOfWeek(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.with(DayOfWeek.SUNDAY);
    }

    /**
     * Lấy ngày đầu năm
     */
    public static LocalDate getFirstDayOfYear(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.withDayOfYear(1);
    }

    /**
     * Lấy ngày cuối năm
     */
    public static LocalDate getLastDayOfYear(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.withDayOfYear(date.lengthOfYear());
    }

    /**
     * Kiểm tra ngày có nằm trong khoảng không
     */
    public static boolean isBetween(LocalDate date, LocalDate start, LocalDate end) {
        if (date == null || start == null || end == null) {
            return false;
        }
        return !date.isBefore(start) && !date.isAfter(end);
    }

    /**
     * Kiểm tra thời điểm có nằm trong khoảng không
     */
    public static boolean isBetween(LocalDateTime dateTime, LocalDateTime start, LocalDateTime end) {
        if (dateTime == null || start == null || end == null) {
            return false;
        }
        return !dateTime.isBefore(start) && !dateTime.isAfter(end);
    }

    /**
     * Tính tuổi từ ngày sinh
     */
    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, today()).getYears();
    }

    /**
     * Thêm số ngày
     */
    public static LocalDate addDays(LocalDate date, int days) {
        if (date == null) {
            return null;
        }
        return date.plusDays(days);
    }

    /**
     * Thêm số tháng
     */
    public static LocalDate addMonths(LocalDate date, int months) {
        if (date == null) {
            return null;
        }
        return date.plusMonths(months);
    }

    /**
     * Thêm số năm
     */
    public static LocalDate addYears(LocalDate date, int years) {
        if (date == null) {
            return null;
        }
        return date.plusYears(years);
    }

    /**
     * Set thời gian bắt đầu ngày (00:00:00)
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay();
    }

    /**
     * Set thời gian kết thúc ngày (23:59:59)
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atTime(23, 59, 59, 999999999);
    }

    /**
     * Format duration thành string dễ đọc
     * Ví dụ: 125 phút -> "2 giờ 5 phút"
     */
    public static String formatDuration(long minutes) {
        if (minutes < 60) {
            return minutes + " phút";
        }
        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;
        if (remainingMinutes == 0) {
            return hours + " giờ";
        }
        return hours + " giờ " + remainingMinutes + " phút";
    }

    /**
     * Kiểm tra năm nhuận
     */
    public static boolean isLeapYear(int year) {
        return Year.of(year).isLeap();
    }
}
