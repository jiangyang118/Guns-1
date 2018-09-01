package com.stylefeng.guns.core.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
