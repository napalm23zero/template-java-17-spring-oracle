package com.hustletech.template.domain.specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public abstract class _GenericSpecification {

    public static <Entity, FilterDTO> Specification<Entity> byFilter(FilterDTO filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Field[] fields = FieldCache.getCachedFields(filter.getClass());

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
}
