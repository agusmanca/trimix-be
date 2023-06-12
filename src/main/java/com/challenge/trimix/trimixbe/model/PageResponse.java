package com.challenge.trimix.trimixbe.model;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private int totalPages;
    private long totalElements;
    private int size;
    private int page;
    private List<T> content;
}
