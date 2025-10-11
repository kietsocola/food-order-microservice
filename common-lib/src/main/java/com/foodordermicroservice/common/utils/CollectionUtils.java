package com.foodordermicroservice.common.utils;

import lombok.experimental.UtilityClass;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Utility class for Collection operations
 */
@UtilityClass
public class CollectionUtils {

    /**
     * Kiểm tra collection null hoặc empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Kiểm tra collection không null và không empty
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Kiểm tra map null hoặc empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Kiểm tra map không null và không empty
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Lấy phần tử đầu tiên của list
     */
    public static <T> T first(List<T> list) {
        return isEmpty(list) ? null : list.get(0);
    }

    /**
     * Lấy phần tử cuối cùng của list
     */
    public static <T> T last(List<T> list) {
        return isEmpty(list) ? null : list.get(list.size() - 1);
    }

    /**
     * Lấy phần tử tại index (an toàn, không throw exception)
     */
    public static <T> T get(List<T> list, int index) {
        if (isEmpty(list) || index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    /**
     * Lấy phần tử tại index với giá trị mặc định
     */
    public static <T> T get(List<T> list, int index, T defaultValue) {
        T value = get(list, index);
        return value != null ? value : defaultValue;
    }

    /**
     * Safe list - trả về empty list nếu null
     */
    public static <T> List<T> safe(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    /**
     * Safe set - trả về empty set nếu null
     */
    public static <T> Set<T> safe(Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

    /**
     * Safe map - trả về empty map nếu null
     */
    public static <K, V> Map<K, V> safe(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    /**
     * Filter list theo điều kiện
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /**
     * Map list sang list mới
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * Tìm phần tử đầu tiên thỏa điều kiện
     */
    public static <T> T findFirst(List<T> list, Predicate<T> predicate) {
        if (isEmpty(list)) {
            return null;
        }
        return list.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    /**
     * Kiểm tra có phần tử nào thỏa điều kiện không
     */
    public static <T> boolean anyMatch(List<T> list, Predicate<T> predicate) {
        if (isEmpty(list)) {
            return false;
        }
        return list.stream().anyMatch(predicate);
    }

    /**
     * Kiểm tra tất cả phần tử có thỏa điều kiện không
     */
    public static <T> boolean allMatch(List<T> list, Predicate<T> predicate) {
        if (isEmpty(list)) {
            return true;
        }
        return list.stream().allMatch(predicate);
    }

    /**
     * Đếm số phần tử thỏa điều kiện
     */
    public static <T> long count(List<T> list, Predicate<T> predicate) {
        if (isEmpty(list)) {
            return 0;
        }
        return list.stream().filter(predicate).count();
    }

    /**
     * Group by theo key
     */
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<T, K> keyMapper) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream()
                .collect(Collectors.groupingBy(keyMapper));
    }

    /**
     * Convert list sang map với custom key và value
     */
    public static <T, K, V> Map<K, V> toMap(
            List<T> list, 
            Function<T, K> keyMapper, 
            Function<T, V> valueMapper) {
        if (isEmpty(list)) {
            return Collections.emptyMap();
        }
        return list.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }

    /**
     * Convert list sang set
     */
    public static <T> Set<T> toSet(List<T> list) {
        if (isEmpty(list)) {
            return Collections.emptySet();
        }
        return new HashSet<>(list);
    }

    /**
     * Remove duplicates từ list
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    /**
     * Partition list thành các sub-lists với size cố định
     */
    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (isEmpty(list) || size <= 0) {
            return Collections.emptyList();
        }
        
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }

    /**
     * Join list thành string với delimiter
     */
    public static <T> String join(List<T> list, String delimiter) {
        if (isEmpty(list)) {
            return "";
        }
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(delimiter));
    }

    /**
     * Reverse list
     */
    public static <T> List<T> reverse(List<T> list) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        List<T> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        return reversed;
    }

    /**
     * Sort list theo comparator
     */
    public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        List<T> sorted = new ArrayList<>(list);
        sorted.sort(comparator);
        return sorted;
    }

    /**
     * Get random element từ list
     */
    public static <T> T random(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    /**
     * Shuffle list
     */
    public static <T> List<T> shuffle(List<T> list) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        List<T> shuffled = new ArrayList<>(list);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    /**
     * Union của 2 collections
     */
    public static <T> List<T> union(Collection<T> col1, Collection<T> col2) {
        Set<T> set = new LinkedHashSet<>();
        if (isNotEmpty(col1)) {
            set.addAll(col1);
        }
        if (isNotEmpty(col2)) {
            set.addAll(col2);
        }
        return new ArrayList<>(set);
    }

    /**
     * Intersection của 2 collections
     */
    public static <T> List<T> intersection(Collection<T> col1, Collection<T> col2) {
        if (isEmpty(col1) || isEmpty(col2)) {
            return Collections.emptyList();
        }
        Set<T> set1 = new HashSet<>(col1);
        Set<T> set2 = new HashSet<>(col2);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }

    /**
     * Difference của 2 collections (col1 - col2)
     */
    public static <T> List<T> difference(Collection<T> col1, Collection<T> col2) {
        if (isEmpty(col1)) {
            return Collections.emptyList();
        }
        if (isEmpty(col2)) {
            return new ArrayList<>(col1);
        }
        Set<T> set1 = new HashSet<>(col1);
        Set<T> set2 = new HashSet<>(col2);
        set1.removeAll(set2);
        return new ArrayList<>(set1);
    }


    /**
     * Kiểm tra collection có chứa bất kỳ element nào không
     */
    public static <T> boolean containsAny(Collection<T> collection, Collection<T> elements) {
        if (isEmpty(collection) || isEmpty(elements)) {
            return false;
        }
        return elements.stream().anyMatch(collection::contains);
    }

    /**
     * Extract IDs từ list objects
     */
    public static <T> List<Long> extractIds(List<T> list, Function<T, Long> idExtractor) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream()
                .map(idExtractor)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Sum values từ list
     */
    public static <T> double sum(List<T> list, Function<T, Number> valueExtractor) {
        if (isEmpty(list)) {
            return 0.0;
        }
        return list.stream()
                .map(valueExtractor)
                .filter(Objects::nonNull)
                .mapToDouble(Number::doubleValue)
                .sum();
    }

    /**
     * Average values từ list
     */
    public static <T> double average(List<T> list, Function<T, Number> valueExtractor) {
        if (isEmpty(list)) {
            return 0.0;
        }
        return list.stream()
                .map(valueExtractor)
                .filter(Objects::nonNull)
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }

    /**
     * Max value từ list
     */
    public static <T> Double max(List<T> list, Function<T, Number> valueExtractor) {
        if (isEmpty(list)) {
            return null;
        }
        return list.stream()
                .map(valueExtractor)
                .filter(Objects::nonNull)
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(0.0);
    }

    /**
     * Min value từ list
     */
    public static <T> Double min(List<T> list, Function<T, Number> valueExtractor) {
        if (isEmpty(list)) {
            return null;
        }
        return list.stream()
                .map(valueExtractor)
                .filter(Objects::nonNull)
                .mapToDouble(Number::doubleValue)
                .min()
                .orElse(0.0);
    }

    /**
     * Chunk list into multiple lists
     */
    public static <T> List<List<T>> chunk(List<T> list, int chunkSize) {
        return partition(list, chunkSize);
    }

    /**
     * Flatten nested lists
     */
    public static <T> List<T> flatten(List<List<T>> nestedList) {
        if (isEmpty(nestedList)) {
            return Collections.emptyList();
        }
        return nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}