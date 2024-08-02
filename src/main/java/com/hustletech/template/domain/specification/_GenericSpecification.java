package com.hustletech.template.domain.specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public abstract class _GenericSpecification {

    private static final Map<Class<?>, Field[]> fieldCache = new HashMap<>();

    public static <Entity, FilterDTO> Specification<Entity> byFilter(FilterDTO filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Field[] fields = getCachedFields(filter.getClass());

            for (Field field : fields) {
                try {
                    Object value = field.get(filter);
                    if (value != null && root.getModel().getAttributes().stream()
                            .anyMatch(a -> a.getName().equals(field.getName()))) {
                        predicates.add(builder.equal(root.get(field.getName()), value));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error accessing field: " + field.getName(), e);
                }
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Field[] getCachedFields(Class<?> clazz) {
        return fieldCache.computeIfAbsent(clazz, cls -> {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
            }
            return fields;
        });
    }
}
