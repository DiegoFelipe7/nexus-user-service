package co.com.nexus.model.shared.pagination;


import java.util.List;

public record PagingResult<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
){}

