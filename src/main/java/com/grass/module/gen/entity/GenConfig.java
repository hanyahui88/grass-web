/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.gen.entity;

import com.grass.common.persistence.CommonEntiry;
import com.grass.module.sys.dic.entity.DictEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 生成方案Entity
 *
 * @author hanyahui
 * @version 2013-10-15
 */
@XmlRootElement(name = "config")
public class GenConfig extends CommonEntiry<GenConfig> implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<GenCategory> categoryList;    // 代码模板分类
    private List<DictEntity> javaTypeList;        // Java类型
    private List<DictEntity> queryTypeList;        // 查询类型
    private List<DictEntity> showTypeList;        // 显示类型

    public GenConfig() {
        super();
    }

    @XmlElementWrapper(name = "category")
    @XmlElement(name = "category")
    public List<GenCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<GenCategory> categoryList) {
        this.categoryList = categoryList;
    }

    @XmlElementWrapper(name = "javaType")
    @XmlElement(name = "dict")
    public List<DictEntity> getJavaTypeList() {
        return javaTypeList;
    }

    public void setJavaTypeList(List<DictEntity> javaTypeList) {
        this.javaTypeList = javaTypeList;
    }

    @XmlElementWrapper(name = "queryType")
    @XmlElement(name = "dict")
    public List<DictEntity> getQueryTypeList() {
        return queryTypeList;
    }

    public void setQueryTypeList(List<DictEntity> queryTypeList) {
        this.queryTypeList = queryTypeList;
    }

    @XmlElementWrapper(name = "showType")
    @XmlElement(name = "dict")
    public List<DictEntity> getShowTypeList() {
        return showTypeList;
    }

    public void setShowTypeList(List<DictEntity> showTypeList) {
        this.showTypeList = showTypeList;
    }

}