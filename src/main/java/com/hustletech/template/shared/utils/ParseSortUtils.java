package com.hustletech.template.shared.utils;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class ParseSortUtils {

    public static Sort parseSortParameter(String sort) {
        if (!StringUtils.hasText(sort)) {
            return Sort.unsorted();
        }

        String[] sortParams = sort.split(",");
        if (sortParams.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid sort parameter format");
        }

        Sort.Order[] orders = new Sort.Order[sortParams.length / 2];
        for (int i = 0; i < sortParams.length; i += 2) {
            String property = sortParams[i];
            Sort.Direction direction;
            try {
                direction = Sort.Direction.fromString(sortParams[i + 1]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid sort direction: " + sortParams[i + 1]);
            }
            orders[i / 2] = new Sort.Order(direction, property);
        }

        return Sort.by(orders);
    }
}
