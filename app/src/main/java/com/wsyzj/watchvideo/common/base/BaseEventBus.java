package com.wsyzj.watchvideo.common.base;

/**
 * @author 焦洋
 * @date 2017/12/7 9:50
 * @Description: $desc$
 */
public class BaseEventBus<T> {

    public int code;
    public T data;

    public BaseEventBus(int code) {
        this.code = code;
    }

    public BaseEventBus(int code, T data) {
        this.code = code;
        this.data = data;
    }
}
