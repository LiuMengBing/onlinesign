package com.example.dictionary.bean.form;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 修改字典及字典项的参数表单
 */
public class UpdateDictionaryForm {

    @NotNull
    @Min(value = 1)
    private Integer id;

    @NotBlank
    private String value;

    @NotNull
    @Range(min=0,max=1)
    private Integer state;

    @NotNull
    @Min(value = 0)
    private Integer sort;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
