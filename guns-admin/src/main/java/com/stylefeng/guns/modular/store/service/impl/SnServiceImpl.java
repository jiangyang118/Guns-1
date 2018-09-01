/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.service.impl
 * File Name:SnServiceImpl.java
 * Date:2018年9月1日 下午2:31:46
 * 
 */
package com.stylefeng.guns.modular.store.service.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.core.redis.RedisService;
import com.stylefeng.guns.core.redis.SnKey;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.store.service.SnService;

/**
 * ClassName: SnServiceImpl <br/>
 * Description: 编号生成类 <br/>
 * Date: 2018年9月1日 下午2:31:46 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Service
public class SnServiceImpl implements SnService {

    private static final String SN_KEY = "SN_KEY";

    @Autowired
    private RedisService redisService;

    @Override
    public synchronized String get() {
        AtomicInteger num = new AtomicInteger(redisService.get(SnKey.SN, SN_KEY, Integer.class));
        String res = DateUtil.getAllTime() + num;
        redisService.set(SnKey.SN, SN_KEY, num.addAndGet(1));
        return res;
    }

    @Override
    public void set() {
        if (null == getRedisSn()) {
            redisService.set(SnKey.SN, SN_KEY, 0);
        }
    }

    @Override
    public Integer getRedisSn() {
        return redisService.get(SnKey.SN, SN_KEY, Integer.class);
    }
}
