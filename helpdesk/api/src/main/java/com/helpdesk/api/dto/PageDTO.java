package com.helpdesk.api.dto;

import java.util.List;

import org.springframework.data.domain.Page;


public final class PageDTO<D> {

    private final List<D> elements;
    private final long index;
    private final long size;
    private final boolean first;
    private final boolean last;
    private final long totalPages;
    private final long totalElements;

    private PageDTO(
            List<D> elements,
            long index,
            long size,
            boolean first,
            boolean last,
            long totalPages,
            long totalElements) {

        this.elements = elements;
        this.index = index;
        this.size = size;
        this.first = first;
        this.last = last;
        this.totalPages = totalPages;
        this.totalElements = totalElements;

    }

    public List<D> getElements() {
        return elements;
    }

    public long getIndex() {
        return index;
    }

    public long getSize() {
        return size;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public static <D> PageDTO<D> from(Page<D> page) {

        return new PageDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.isFirst(),
                page.isLast(),
                page.getTotalPages(),
                page.getTotalElements());

    }

}
