package com.wsyzj.watchvideo.common.http;

import java.io.Serializable;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class BaseEntity<T> implements Serializable{

    public int code;
    public String msg;

    public T t;
}
