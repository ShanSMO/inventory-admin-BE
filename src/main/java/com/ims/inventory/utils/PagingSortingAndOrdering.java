package com.ims.inventory.utils;

import com.ims.inventory.enums.SortingOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PagingSortingAndOrdering {

    public static Pageable createPageRequest(int start, int size) {
      return PageRequest.of(start,size);
    }

    public static Pageable createPageRequest(int start, int size, String sortingField, SortingOrder sortOrder) {
        Sort.Direction direction = (sortOrder == SortingOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC );
        return  PageRequest.of(start,size, direction, sortingField);
    }

}
