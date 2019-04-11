/*
 * Copyright (c) 2014-2015, Yunnex and/or its affiliates. All rights reserved. Use, Copy is subject to authorized license.
 */
package com.sakyone.entity;

import com.sakyone.common.entity.BaseEntity;

import java.util.Date;

/**
 * 灰度用户表 实体类
 * 
 * @author Ternence
 * @date 2017-06-01
 */
public class GrayUser extends BaseEntity {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键列
     * id 
     */
    private Long id;

    /**
     * 
     * 用户唯一标识 
     */
    private String userId;

    /**
     * 
     * 用户类型 
     */
    private String userType;

    /**
     * 
     * 用户备注 
     */
    private String remark;

    /**
     * 
     * 用户分组ID 
     */
    private Long groupId;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 
     * createTime 
     */
    private Date createTime;

    /**
     * 
     * updateTime 
     */
    private Date updateTime;
    /**
     * 微信公众号appId
     */
    private String wxappId;

    /**
     * 微信小程序appId
     */
    private String xcxappId;

	
	public GrayUser(){
	    // default constructor
	}
    
    public GrayUser(Long id){
        this.id = id;
    }

    public String getWxappId() {
        return wxappId;
    }

    public void setWxappId(String wxappId) {
        this.wxappId = wxappId;
    }

    public String getXcxappId() {
        return xcxappId;
    }

    public void setXcxappId(String xcxappId) {
        this.xcxappId = xcxappId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getUserType() {
        return this.userType;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    


}
