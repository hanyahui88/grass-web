package com.grass.common.persistence;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 韩亚辉 on 2016/4/19.
 */
public abstract class CommonEntiry {
    protected String remarks;    // 备注
    protected String createUser;    // 创建者
    protected String createUserName;    // 创建者
    protected Date createDate;    // 创建日期
    protected String updateUserName;    // 更新者
    protected String updateUser;    // 更新者
    protected Date updateDate;    // 更新日期
    protected String delFlag;    // 删除标记（0：正常；1：删除；2：审核）


    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";
    protected String id;
    protected Boolean newRecord;

    public CommonEntiry() {
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public CommonEntiry(String id) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        setId(UUID.randomUUID() + "");
//        User user = UserUtils.getUser();
//        if (StringUtils.isNotBlank(user.getId())){
//            this.updateBy = user;
//            this.createBy = user;
//        }
        this.createDate = this.updateDate;
    }

    public void preUpdate() {
//        User user = UserUtils.getUser();
//        if (StringUtils.isNotBlank(user.getId())){
//            this.updateBy = user;
//        }
        this.updateDate = new Date();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
