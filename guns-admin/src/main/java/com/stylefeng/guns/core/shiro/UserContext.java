/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.core.shiro
 * File Name:UserContext.java
 * Date:2018年8月27日 下午7:03:51
 * 
 */
package com.stylefeng.guns.core.shiro;

import com.stylefeng.guns.modular.system.model.User;

/**
 * ClassName: UserContext <br/>
 * Description: 用户线程类 <br/>
 * Date: 2018年8月27日 下午7:03:51 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class UserContext {

    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }
}
