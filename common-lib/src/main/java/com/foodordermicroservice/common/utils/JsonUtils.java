package com.foodordermicroservice.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * Utility class for JSON operations using Jackson
 */
@Slf4j
@UtilityClass
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // Cấu hình ObjectMapper
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    /**
     * Get ObjectMapper instance
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * Convert object sang JSON string
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể chuyển đổi object sang JSON", e);
        }
    }

    /**
     * Convert object sang JSON string (pretty print)
     */
    public static String toJsonPretty(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON (pretty): {}", e.getMessage(), e);
            throw new RuntimeException("Không thể chuyển đổi object sang JSON", e);
        }
    }

    /**
     * Parse JSON string sang object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON to object: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể parse JSON sang object", e);
        }
    }

    /**
     * Parse JSON string sang object (với TypeReference cho generic types)
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON to object: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể parse JSON sang object", e);
        }
    }

    /**
     * Parse JSON string sang List
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(
                json, 
                OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz)
            );
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON to List: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể parse JSON sang List", e);
        }
    }

    /**
     * Parse JSON string sang Map
     */
    public static Map<String, Object> fromJsonToMap(String json) {
        return fromJson(json, new TypeReference<Map<String, Object>>() {});
    }

    /**
     * Convert object sang Map
     */
    public static Map<String, Object> toMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return OBJECT_MAPPER.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }

    /**
     * Convert Map sang object
     */
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        return OBJECT_MAPPER.convertValue(map, clazz);
    }

    /**
     * Clone object bằng cách serialize và deserialize
     */
    public static <T> T clone(T obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            String json = toJson(obj);
            return fromJson(json, clazz);
        } catch (Exception e) {
            log.error("Error cloning object: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể clone object", e);
        }
    }

    /**
     * Merge 2 objects (object2 sẽ override các field của object1)
     */
    public static <T> T merge(T object1, T object2, Class<T> clazz) {
        if (object1 == null) {
            return object2;
        }
        if (object2 == null) {
            return object1;
        }
        try {
            Map<String, Object> map1 = toMap(object1);
            Map<String, Object> map2 = toMap(object2);
            map1.putAll(map2);
            return fromMap(map1, clazz);
        } catch (Exception e) {
            log.error("Error merging objects: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể merge objects", e);
        }
    }

    /**
     * Kiểm tra string có phải JSON hợp lệ không
     */
    public static boolean isValidJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        try {
            OBJECT_MAPPER.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    /**
     * Convert object sang byte array
     */
    public static byte[] toBytes(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to bytes: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể chuyển đổi object sang bytes", e);
        }
    }

    /**
     * Parse byte array sang object
     */
    public static <T> T fromBytes(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (Exception e) {
            log.error("Error parsing bytes to object: {}", e.getMessage(), e);
            throw new RuntimeException("Không thể parse bytes sang object", e);
        }
    }

    /**
     * Get nested value from JSON path
     * Example: getValueByPath(json, "user.address.city")
     */
    public static Object getValueByPath(String json, String path) {
        if (StringUtils.isEmpty(json) || StringUtils.isEmpty(path)) {
            return null;
        }
        try {
            String[] keys = path.split("\\.");
            Map<String, Object> map = fromJsonToMap(json);
            Object value = map;
            
            for (String key : keys) {
                if (value instanceof Map) {
                    value = ((Map<?, ?>) value).get(key);
                } else {
                    return null;
                }
            }
            return value;
        } catch (Exception e) {
            log.error("Error getting value by path: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * Update nested value in JSON by path
     */
    @SuppressWarnings("unchecked")
    public static String updateValueByPath(String json, String path, Object newValue) {
        if (StringUtils.isEmpty(json) || StringUtils.isEmpty(path)) {
            return json;
        }
        try {
            String[] keys = path.split("\\.");
            Map<String, Object> map = fromJsonToMap(json);
            
            Map<String, Object> current = map;
            for (int i = 0; i < keys.length - 1; i++) {
                current = (Map<String, Object>) current.get(keys[i]);
                if (current == null) {
                    return json;
                }
            }
            
            current.put(keys[keys.length - 1], newValue);
            return toJson(map);
        } catch (Exception e) {
            log.error("Error updating value by path: {}", e.getMessage(), e);
            return json;
        }
    }

    /**
     * Compare 2 JSON strings
     */
    public static boolean equals(String json1, String json2) {
        if (json1 == null && json2 == null) {
            return true;
        }
        if (json1 == null || json2 == null) {
            return false;
        }
        try {
            Object obj1 = OBJECT_MAPPER.readTree(json1);
            Object obj2 = OBJECT_MAPPER.readTree(json2);
            return obj1.equals(obj2);
        } catch (Exception e) {
            return false;
        }
    }
}