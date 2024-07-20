package com.hustletech.template.shared.utils;

import org.springframework.data.domain.Sort;

public class ParseSortUtils {

    /**
     * Parses a sorting parameter string into a Spring Data Sort object.
     * The string is expected to contain multiple entries separated by commas,
     * where each entry consists of a field name followed by a space and a sort
     * direction (asc or desc).
     * 
     * @param sort String containing the sort criteria.
     * @return Sort object representing the sorting parameters.
     */
    public static Sort parseSortParameter(String sort) {
        String[] sortFields = sort.split(",");
        Sort finalSort = Sort.unsorted();
        for (String fieldSort : sortFields) {
            String[] split = fieldSort.trim().split(" ");
            if (split.length == 2) {
                String field = split[0];
                Sort.Direction direction = Sort.Direction.fromString(split[1]);
                finalSort = finalSort.and(Sort.by(direction, field));
            } else {
                throw new IllegalArgumentException("Invalid sort parameter format");
            }
        }
        return finalSort;
    }

}
