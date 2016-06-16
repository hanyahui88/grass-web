package com.grass.common.persistence;

import com.grass.common.utils.IdWorkerUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * Created by 韩亚辉 on 2016/4/19.
 */
public abstract class BaseEntiry {
    protected String remarks;    // 备注
    protected Long createUser;    // 创建者
    protected String createUserName;    // 创建者
    protected Date createDate;    // 创建日期
    protected String updateUserName;    // 更新者
    protected Long updateUser;    // 更新者
    protected Date updateDate;    // 更新日期
    protected int delFlag;    // 删除标记（0：正常；1：删除；2：审核）


    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final int DEL_FLAG_NORMAL = 0;
    public static final int DEL_FLAG_DELETE = 1;
    public static final int DEL_FLAG_AUDIT = 2;
    protected Long id;
    protected Boolean newRecord;

    public BaseEntiry() {
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public BaseEntiry(Long id) {
        this();
        this.id = id;
    }


    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     *
     * @return
     */
    public Boolean getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(Boolean newRecord) {
        this.newRecord = newRecord;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        setId(IdWorkerUtils.nextId());
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public void preUpdate() {
        this.updateDate = new Date();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
