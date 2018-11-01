package com.example.dictionary.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 字典树
 */
public class DictionaryTree implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer parentId;

    private Integer key;

    private String value;

    private Integer sort;

    private String note;

    private Boolean readonly;

    private List<DictionaryTree> children;

    public String findInChildren(Integer key){
        for(int i=0; i<children.size(); i++){
            if(children.get(i).getKey().equals(key)){
                return children.get(i).getValue();
            }
        }
        return "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public List<DictionaryTree> getChildren() {
        return children;
    }

    public void setChildren(List<DictionaryTree> children) {
        this.children = children;
    }
}
