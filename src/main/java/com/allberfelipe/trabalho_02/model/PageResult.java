package com.allberfelipe.trabalho_02.model;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResult<T>(
        long numberOfItems,
        int numberOfPages,
        int currentPage,
        List<T> items
) {

    public PageResult(Page<T> page) {
        this(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent()
        );
    }
}
