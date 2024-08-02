package com.hustletech.template.shared.utils;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * Utility class for parsing string-based sorting parameters into Spring Data
 * Sort objects.
 * This class supports converting a comma-separated string that represents field
 * names and
 * directions into a Sort object that can be used in data queries.
 */
public class ParseSortUtil {

    /**
     * Parses a sort parameter string and creates a corresponding Sort object.
     * The input string should follow the format
     * "field,direction,field,direction,...",
     * where direction is either "asc" for ascending or "desc" for descending.
     * 
     * @param sort A string representing the sorting parameters
     * @return A Sort object representing the order specified in the string
     * @throws IllegalArgumentException if the sort string is malformed
     */
    public static Sort parseSortParameter(String sort) {
        if (!StringUtils.hasText(sort)) {
            return Sort.unsorted(); // Return an unsorted object if the input string is empty
        }

        String[] sortParams = sort.split(",");
        // Ensure the sort parameter string contains pairs of field and direction
        if (sortParams.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid sort parameter format. Parameters must be in pairs.");
        }

        Sort.Order[] orders = new Sort.Order[sortParams.length / 2];
        for (int i = 0; i < sortParams.length; i += 2) {
            String property = sortParams[i];
            Sort.Direction direction;
            try {
                direction = Sort.Direction.fromString(sortParams[i + 1]);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid sort direction: " + sortParams[i + 1], e);
            }
            orders[i / 2] = new Sort.Order(direction, property);
        }

        return Sort.by(orders);
    }
}
