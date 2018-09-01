/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-core
 * Package Name:com.stylefeng.guns.core.constant
 * File Name:ApiConstant.java
 * Date:2018年8月28日 上午10:49:46
 * 
 */
package com.stylefeng.guns.core.constant;

/**
 * ClassName: ApiConstant <br/>
 * Description: api的常量池 <br/>
 * Date: 2018年8月28日 上午10:49:46 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class ApiConstants {

    private ApiConstants() {
        super();
    }

    public static final int PAGE_SIZE = 20;

    /**
     * 任务开启的最低接受人数
     */
    public static final int START_NUM = 2;

    public static final String checkStatusStringDoing = "当前状态不是检查中";

    public static final String checkStatusStringBegin = "当前状态不是未检查";

    public static final String checkUserCanDo = "当前登录账号不可操作";

    public static final String checkTakDetail = "当前任务已经操作，不能重复操作";

    public static final String checkReceive = "任务检查项已经提交，不能重复操作";

    public static final String checkNOTNULL = "参数不能为空";

    public static final String checkCreateStores = "被检查的店铺不能为空";

    public static final String checkCreateAssignees = "任务执行者不能为空";
}
