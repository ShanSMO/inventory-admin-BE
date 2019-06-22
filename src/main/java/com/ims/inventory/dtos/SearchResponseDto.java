package com.ims.inventory.dtos;

import java.util.List;

public class SearchResponseDto {

    private String status;
    private int currentPage;
    private int recordsPerPage;
    private Long recordCount;
    private Object searchObject;
    private List<?> searchItems;

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

    public List<?> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(List<?> searchItems) {
        this.searchItems = searchItems;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
