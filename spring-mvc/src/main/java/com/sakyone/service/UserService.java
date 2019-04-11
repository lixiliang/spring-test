package com.sakyone.service;

import com.google.common.collect.Maps;
import com.sakyone.dao.GrayUserMapper;
import com.sakyone.entity.GrayUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lixiliang on 2019/4/9.
 */
@Service
public class UserService {
    @Autowired
    private GrayUserMapper grayUserMapper;

    public GrayUser findUserById(Long id){
        Map map = Maps.newHashMap();
        map.put("id",id);
        GrayUser user = grayUserMapper.get(id);
        return user;
    }
}
