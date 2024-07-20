package com.hustletech.template.domain.specification;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldCache {

    private static final Map<Class<?>, Field[]> fieldCache = new HashMap<>();

    public static Field[] getCachedFields(Class<?> clazz) {
        return fieldCache.computeIfAbsent(clazz, cls -> {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
            }
            return fields;
        });
    }
}
