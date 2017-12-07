package com.wsyzj.watchvideo.common.base;

/**
 * @author 焦洋
 * @date 2017/12/7 9:50
 * @Description: $desc$
 */
public class BaseEvent<T> {

    public int code;
    public T data;

    public BaseEvent(int code) {
        this.code = code;
    }

    public BaseEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }
}
