package com.wsyzj.watchvideo.common.base.http;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class BaseEntity<T> {
    public int code;
    public String msg;
    public T data;
}
