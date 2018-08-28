package com.stylefeng.guns.fastjson;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.SimpleObject;
import com.stylefeng.guns.rest.modular.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.rest.modular.auth.security.impl.Base64SecurityAction;

/**
 * json测试
 *
 * @author fengshuonan
 * @date 2017-08-25 16:11
 */

public class JsonTest {

    public static void main(String[] args) {

        Base64SecurityAction action = new Base64SecurityAction();

        String randomKey = "b1if28";

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setUser("fsn");
        String json = JSON.toJSONString(simpleObject);
        String encode = action.doAction(json);
        baseTransferEntity.setObject(encode);

        // md5签名
        // String encrypt = MD5Util.encrypt(json + randomKey);
        String encrypt = MD5Util.encrypt(baseTransferEntity.getObject() + randomKey);
        baseTransferEntity.setSign(encrypt);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}
