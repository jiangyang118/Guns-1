/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-core
 * Package Name:com.stylefeng.guns.core.redis
 * File Name:SnKey.java
 * Date:2018年9月1日 下午2:32:43
 * 
 */
package com.stylefeng.guns.core.redis;

/**
 * ClassName: SnKey <br/>
 * Description: TODO <br/>
 * Date: 2018年9月1日 下午2:32:43 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class SnKey extends BasePrefix {

    private SnKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static final SnKey SN = new SnKey(0, "sn");
}
