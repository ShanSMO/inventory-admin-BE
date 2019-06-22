package com.ims.inventory.dtos.CustomDtos;


import java.util.List;

public class ChartResponseDto {

    private Object key;
    private Object value;
    private List<Object> keyList;
    private List<Object> valueList;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Object> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Object> keyList) {
        this.keyList = keyList;
    }

    public List<Object> getValueList() {
        return valueList;
    }

    public void setValueList(List<Object> valueList) {
        this.valueList = valueList;
    }
}
