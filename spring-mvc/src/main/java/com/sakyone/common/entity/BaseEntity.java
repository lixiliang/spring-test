package com.sakyone.common.entity;

import com.alibaba.fastjson.JSON;

/**
 * 持久化对象基类，该类的派生者需与数据库的字段一一对应
 *
 * @author Zhoutao
 * @date 2014-7-29
 */
public class BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
