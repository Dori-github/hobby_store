package kr.spring.util;

import java.util.*;
import java.lang.reflect.Field;

public class ConvertUtils {
    private ConvertUtils() {}
    
    // VO -> Map
    public static Map<String, Object> convertToMap(Object obj) {
        try {
            if (Objects.isNull(obj)) {
                return Collections.emptyMap();
            }
            Map<String, Object> convertMap = new HashMap<>();
 
            Field[] fields = obj.getClass().getDeclaredFields();
 
            for (Field field : fields) {
                field.setAccessible(true);
                convertMap.put(field.getName(), field.get(obj));
            }
            return convertMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    
    // Map -> VO
    public static <T> T convertToValueObject(Map<String, Object> map, Class<T> type) {
        try {
            if (Objects.isNull(type)) {
                throw new NullPointerException("Class cannot be null");
            }
            if (Objects.isNull(map) || map.isEmpty()) {
                throw new IllegalArgumentException("map is null or empty");
            }
 
            T instance = type.getConstructor().newInstance();
            Field[] fields = type.getDeclaredFields();
 
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                for (Field field : fields) {
                    if (entry.getKey().equals(field.getName())) {
                        field.setAccessible(true);
 
                        Object value = Objects.isNull(entry.getValue()) && field.getType().isPrimitive()
                                ? getDefaultValue(field.getType())
                                : map.get(field.getName());
 
                        field.set(instance, value);
                        break;
                    }
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    private static Object getDefaultValue(Class<?> type) {
        switch (type.getName()) {
            case "byte": case "short": case "int": return 0;
            case "long"    : return 0L;
            case "float"   : return 0.0f;
            case "double"  : return 0.0d;
            case "char"    : return '\u0000';
            case "boolean" : return false;
        }
        return null;
    }
}
