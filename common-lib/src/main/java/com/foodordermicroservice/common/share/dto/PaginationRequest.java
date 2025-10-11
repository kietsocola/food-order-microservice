package com.foodordermicroservice.common.share.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationRequest {
    private int page;
    private int size;
    private String search;
    private List<String> sort;
    private Map<String, String> filters;

    public int getOffset() {
        return page * size;
    }
    public boolean isSorted() {
        return sort != null && !sort.isEmpty();
    }
    public boolean isFiltered() {
        return filters != null && !filters.isEmpty();
    }
    public boolean isSearched() {
        return search != null && !search.isEmpty();
    }
}
