package com.order.management.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.order.management.exception.OrderManagementError;
import com.order.management.exception.OrderManagementException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
public class ListInfo {
    private Integer page;
    private Long totalCount;
    private Integer pageCount;
    private Integer pageSize;
    private String sortField;
    private SortOrder sortOrder;


    private static final Integer DEFAULT_PAGE_SIZE = 3;
    private static final Integer DEFAULT_PAGE = 0;

    public ListInfo(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public ListInfo(Integer page, Integer pageSize, String sortField, SortOrder sortOrder) {
        this(page, pageSize);
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }


    @SneakyThrows
    public static ListInfo getListInfo(Page<?> page, ListInfo defaultListInfo) {
        if (page.getTotalElements() == 0L && page.getTotalPages() == 1 && (defaultListInfo != null ? defaultListInfo.getPage() : DEFAULT_PAGE) > 0) {
            throw new OrderManagementException(OrderManagementError.INVALID_PAGE_NUMBER, new String[0]);
        }

        int pageNumber = page.getTotalElements() == 0L ? (defaultListInfo != null ? defaultListInfo.getPage() : DEFAULT_PAGE) : page.getPageable().getPageNumber();
        int pageSize = page.getTotalElements() == 0L ? (defaultListInfo != null ? defaultListInfo.getPageSize() : DEFAULT_PAGE_SIZE) : page.getPageable().getPageSize();

        return new ListInfo(
                pageNumber,
                page.getTotalElements(),
                page.getTotalPages(),
                pageSize,
                defaultListInfo != null ? defaultListInfo.getSortField() : null,
                defaultListInfo != null ? defaultListInfo.getSortOrder() : null
        );
    }

    public static ListInfo defaultListInfo() {
        return new ListInfo(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
    }

    public static Pageable toPageable(ListInfo listInfo) {
        SortOrder sortOrder = listInfo.getSortOrder() != null ? listInfo.getSortOrder() : SortOrder.ASC;
        Sort sortBy = null;

        if (listInfo.getSortField() != null && !listInfo.getSortField().isEmpty()) {
            sortBy = Sort.by(listInfo.getSortField());
        }

        if (listInfo.getSortOrder() != null && sortBy != null) {
            sortBy = sortOrder == SortOrder.DESC ? sortBy.descending() : sortBy.ascending();
        }

        return sortBy != null ?
                PageRequest.of(listInfo.getPage() != null ? listInfo.getPage() : DEFAULT_PAGE,
                        listInfo.getPageSize() != null ? listInfo.getPageSize() : DEFAULT_PAGE_SIZE,
                        sortBy)
                : PageRequest.of(listInfo.getPage() != null ? listInfo.getPage() : DEFAULT_PAGE,
                listInfo.getPageSize() != null ? listInfo.getPageSize() : DEFAULT_PAGE_SIZE);
    }
}
