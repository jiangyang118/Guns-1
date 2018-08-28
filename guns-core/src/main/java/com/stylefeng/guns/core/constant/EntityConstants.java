/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-core
 * Package Name:com.stylefeng.guns.core.constant
 * File Name:EntityConstants.java
 * Date:2018年8月28日 下午4:01:26
 * 
 */
package com.stylefeng.guns.core.constant;

/**
 * ClassName: EntityConstants <br/>
 * Description: 实体类常量池 <br/>
 * Date: 2018年8月28日 下午4:01:26 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */

public class EntityConstants {

    /** 更新忽略属性 */
    public static final String[] UPDATE_IGNORE_PROPERTIES = new String[] { EntityConstants.CREATE_DATE_PROPERTY_NAME,
            EntityConstants.MODIFY_DATE_PROPERTY_NAME, EntityConstants.VERSION_PROPERTY_NAME };

    /** "创建日期"属性名称 */
    public static final String CREATE_DATE_PROPERTY_NAME = "createDate";

    /** "修改日期"属性名称 */
    public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";

    /** "版本"属性名称 */
    public static final String VERSION_PROPERTY_NAME = "version";

    private EntityConstants() {
        super();
    }
}
