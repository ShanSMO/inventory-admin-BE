package com.ims.inventory.dtos;


import com.ims.inventory.enums.SortingOrder;

public class SearchRequestDto {

    private Integer currentPage = 0;
    private int recordsPerPage = 10;
    private Object searchObject;
    private String sortingField;
    private SortingOrder sortingDirection;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public Object getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(Object searchObject) {
        this.searchObject = searchObject;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getSortingField() {
        return sortingField;
    }

    public void setSortingField(String sortingField) {
        this.sortingField = sortingField;
    }

    public SortingOrder getSortingDirection() {
        return sortingDirection;
    }

    public void setSortingDirection(SortingOrder sortingDirection) {
        this.sortingDirection = sortingDirection;
    }
}
