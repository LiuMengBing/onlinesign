package com.example.dictionary.bean.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加字典及字典项的参数表单
 */
public class AddDictionaryForm {

    @NotNull
    @Min(value = 0)
    private Integer parentId;

    @NotNull
    @Min(value=0)
    @Max(value = 1)
    private Integer type;

    @NotBlank
    private String value;

    @NotNull
    @Min(value = 0)
    private Integer sort;

    private String note;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
